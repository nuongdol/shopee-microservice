package com.example.shopeeIdentityService.Service.Imp;

import com.example.shopeeIdentityService.Dto.Request.LoginRequest;
import com.example.shopeeIdentityService.Dto.Request.RegisterRequest;
import com.example.shopeeIdentityService.Dto.Response.LoginResponse;
import com.example.shopeeIdentityService.Entity.RBAC_ABAC.Roles;
import com.example.shopeeIdentityService.Entity.RBAC_ABAC.UserRoles;
import com.example.shopeeIdentityService.Entity.RBAC_ABAC.Users;
import com.example.shopeeIdentityService.Entity.Token;
import com.example.shopeeIdentityService.Exception.AppException;
import com.example.shopeeIdentityService.Enum.Exception.ErrorCode;
import com.example.shopeeIdentityService.Repository.*;
import com.example.shopeeIdentityService.Service.AuthService;
import com.example.shopeeIdentityService.Util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImp implements AuthService {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    private final RedisService redisService;

    @Override
    @Transactional
    public LoginResponse login(LoginRequest loginRequest, HttpServletResponse response) {
        LoginResponse loginResponse = new LoginResponse();
        Users user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        String accessToken = jwtUtils.generateToken(user);
        String refreshToken = jwtUtils.generateRefreshToken(user);
        //luu refresh token vào cookie
        Cookie refreshTokenCookie = getRefreshTokenCookie(refreshToken);
        //them cookie đến response
        response.addCookie(refreshTokenCookie);
        Token token = new Token();
        token.setRefreshToken(refreshToken);
        tokenRepository.save(token);
        loginResponse.setAccessToken(accessToken);
        return loginResponse;
    }

    private Cookie getRefreshTokenCookie(String token) {
        var cookieMaxAge = (int) (jwtUtils.extractExpiration(token).getTime() - System.currentTimeMillis()) / 1000;
        if (cookieMaxAge < 0)
            cookieMaxAge = 0;
        Cookie refreshTokenCookie = new Cookie("refresh_token", (String) token);
        refreshTokenCookie.setHttpOnly(true);//prevents javascript access (XSS protection)
        refreshTokenCookie.setSecure(true); //ensure https only (important for production
        refreshTokenCookie.setPath("/");//available for the entire application
        refreshTokenCookie.setMaxAge(cookieMaxAge);//set to remaining TTL of the token
        return refreshTokenCookie;
    }

    @Override
    @Transactional
    public void register(RegisterRequest registerRequest) {
        try {
            //save user
            Users users = new Users();
            users.setUsername(registerRequest.getUsername());
            users.setFullName(registerRequest.getFullName());
            users.setEmail(registerRequest.getEmail());
            users.setGender(registerRequest.getGender());
            users.setDateOfBirth(registerRequest.getDateOfBirth());
            users.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
            users.setCreatedAt(LocalDateTime.now());
            userRepository.save(users);
            //lúc đăng ký lần đầu tiên sẽ tự gán là role_user(buyer)
            Optional<Roles> roles = Optional.ofNullable(roleRepository.findByRoleName("Buyer")
                    .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED)));
            UserRoles userRole = new UserRoles();
            userRole.setUser(users);
            userRole.setRoles(roles.get());
            userRoleRepository.save(userRole);
        } catch (Exception exception) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        //check token null
        String accessTokenBearer = request.getHeader("Authorization");
        if (accessTokenBearer == null || !accessTokenBearer.startsWith("Bearer ")) {
            throw new AppException(ErrorCode.INVALID_AUTHORIZATION_HEADER);
        }
        String accessToken = request.getHeader("Authorization").substring(7);
        Cookie refreshTokenCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refresh_token"))
                .findFirst().orElseThrow();
        log.info("Token value from request: {}", accessToken);
        invalidateToken(accessToken);
        invalidateToken(refreshTokenCookie.getValue());
        invalidateRefreshTokenCookie(response, refreshTokenCookie);
    }

    private void invalidateRefreshTokenCookie(HttpServletResponse response, Cookie refreshTokenCookie) {
        refreshTokenCookie.setMaxAge(0);
        refreshTokenCookie.setPath("/");
        response.addCookie(refreshTokenCookie);
    }

    @Override
    public String refreshToken(HttpServletRequest request, HttpServletResponse response) {
        var refreshTokenCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refresh_token"))
                .findFirst().orElseThrow();
        var currentRefreshToken = refreshTokenCookie.getValue();
        if (redisService.hasToken(currentRefreshToken))
            return null;
        invalidateToken(currentRefreshToken);
        if (currentRefreshToken != null) {
            if (!jwtUtils.isTokenExpired(currentRefreshToken)) {
                var email = jwtUtils.extractEmail(currentRefreshToken);
                Users user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
                String jwtToken = jwtUtils.generateToken(user);
                String newRefreshToken = jwtUtils.generateRefreshToken(user);
                refreshTokenCookie = getRefreshTokenCookie(newRefreshToken);
                //add the cookie to the response
                response.addCookie(refreshTokenCookie);
                return jwtToken;
            }
        }
        return null;
    }

    private void invalidateToken(String token) {
        long expirationTime = (jwtUtils.extractExpiration(token).getTime() - System.currentTimeMillis()) / 1000;
        log.info("Invalidating token with remaining : TTL: {} seconds", expirationTime);
        /*
        blacklist nếu token chưa expired
        token hết hạn rồi thì khng cần blacklist nữa
        TTL: thời gian còn lại
         */
        if (expirationTime > 0L) {
            log.info("New Access Token Generated Successfully, Invalidating Previous Refresh token");
            redisService.setToken(token, "blacklisted", expirationTime, TimeUnit.SECONDS);
        }
    }
}
