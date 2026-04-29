package com.example.shopeeIdentityService.Config;

import com.example.shopeeIdentityService.Service.Imp.CustomUserDetailsService;
import com.example.shopeeIdentityService.Util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;//forced dependency
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        final String email;
        final String jwt;
        if((authorizationHeader == null)|| !authorizationHeader.startsWith("Bearer ")){
            //throw exception about authorizationHeader
            filterChain.doFilter(request, response);//the next filter finishs
            return;
        }
        jwt = authorizationHeader.substring(7);
        //throw exception about token if token is not valid
        email = jwtUtils.extractEmail(jwt);
        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            //find user by email
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            if(jwtUtils.isTokenValid(jwt, userDetails)){
                //securityContext save security info of request 's spring security(who is login, authenticate, authorities)
                //securityContext (authentication: username, user's info(principal), roles/authorities, authenticated status)
                //securityContextHolder save securityContext
                /*
                 Nhiệm vụ:
                 - securityContext lưu thông tin người dùng hiện tại(current user)
                 - Cung cấp thông tin user cho toàn bộ hệ thống
                 - Giúp spring security kiểm tra quyền(authorization)
                 - Giữ trạng thái đăng nhập trong 1 request(jwt-> filter->securityContext ->controller)
                 */
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }
        }
        filterChain.doFilter(request, response);
    }
}
