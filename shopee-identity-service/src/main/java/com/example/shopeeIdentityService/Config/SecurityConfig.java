package com.example.shopeeIdentityService.Config;

import com.example.shopeeIdentityService.Security.Oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.example.shopeeIdentityService.Security.Oauth2.Oauth2AuthenticationFailureHandler;
import com.example.shopeeIdentityService.Security.Oauth2.Oauth2AuthenticationSuccessHandler;
import com.example.shopeeIdentityService.Service.Imp.CustomOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // bật bảo mật phương thức @PreAuthorize
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsService userDetailsService;
    private final TokenBlacklistFilter tokenBlacklistFilter;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    private final Oauth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler;
    private final Oauth2AuthenticationFailureHandler oauth2AuthenticationFailureHandler;
    private final CustomOauth2UserService customOauth2UserService;
    private final AuthenticationConfig authenticationConfig;
    /*
    By default, spring oauth2 uses httpSessionOAuth2AuthorizationRequestRepository to save
    the authorization request. But, since our service is stateless, we can't save it in
    the session. We'll save the request in a Base64 encoded cookie instead.
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login", "/api/auth/register", "/api/forgot-password/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")// chỉ định trang đăng nhập tuỳ chỉnh
                                .permitAll()// cho phép tất cả mọi người truy cập trang đăng nhập
                )
                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint(author
                                -> author.baseUri("/oauth2/authorize")
                                .authorizationRequestRepository
                                        (authenticationConfig.cookieOAuth2AuthorizationRequestRepository()))
                        .redirectionEndpoint(redirect -> redirect
                                .baseUri("/oauth2/callback/*"))
                        .userInfoEndpoint(userInfor -> userInfor
                                .userService(customOauth2UserService))
                        .successHandler(oauth2AuthenticationSuccessHandler)
                        .failureHandler(oauth2AuthenticationFailureHandler)
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(tokenBlacklistFilter, LogoutFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(
                        LogoutConfigurer::permitAll //cho phép tất cả mọi người logout
                );
        return http.build();
    }

    private AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(authenticationConfig.passwordEncoder());
        return authenticationProvider;
    }


}
