package com.example.shopeeIdentityService.Controller;

import com.example.shopeeIdentityService.Dto.ApiResponse;
import com.example.shopeeIdentityService.Dto.Request.LoginRequest;
import com.example.shopeeIdentityService.Dto.Request.RegisterRequest;
import com.example.shopeeIdentityService.Dto.Response.LoginResponse;
import com.example.shopeeIdentityService.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginResponse authResponse = authService.login(loginRequest, response);
        //add access token vào header
        response.setHeader("Authorization",
                "Bearer " + authResponse.getAccessToken());
        return ApiResponse.<LoginResponse>builder().result(authResponse).build();
    }

    @PostMapping("/register")
    public ApiResponse<?> register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ApiResponse.builder()
                .message("Register successfully!")
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<?> logout(HttpServletRequest request, HttpServletResponse response) {
        authService.logout(request, response);
        return ApiResponse.builder().build();
    }

    @PostMapping("/refresh")
    public ApiResponse<?> refresh(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = authService.refreshToken(request, response);
        //add access token in header
        if (accessToken != null) {
            response.setHeader("Authorization", "Bearer" + accessToken);
        }
        return ApiResponse.builder().result(accessToken).build();
    }


}
