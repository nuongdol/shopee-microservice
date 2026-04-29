package com.example.shopeeIdentityService.Service;



import com.example.shopeeIdentityService.Dto.Request.LoginRequest;
import com.example.shopeeIdentityService.Dto.Request.RegisterRequest;
import com.example.shopeeIdentityService.Dto.Response.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest, HttpServletResponse response);

    void register(RegisterRequest registerRequest);

    void logout(HttpServletRequest request, HttpServletResponse response);

    String refreshToken(HttpServletRequest request, HttpServletResponse response);
}
