package com.example.shopeeIdentityService.Controller;

import com.example.shopeeIdentityService.Dto.ApiResponse;
import com.example.shopeeIdentityService.Dto.ResetPasswordDto;
import com.example.shopeeIdentityService.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forgot-password")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final UserService userService;

    //send mail for email verification
    @PostMapping("/verify/{email}")
    public ApiResponse<?> verifyEmail(@PathVariable String email){
        userService.verifyMail(email);
        return ApiResponse.builder()
                .message("The user exits in the system !")
                .build();
    }

    @PostMapping("/verifyOtp/{otp}/{email}")
    public ApiResponse<?> verifyOtp(@PathVariable String otp, @PathVariable String email){
        boolean validOtp = userService.verifyOtp(otp, email);
        return validOtp? ApiResponse.builder()
                .message("OTP verified!")
                .build(): ApiResponse.builder().message("Invalid or expired OTP").build();
    }

    @PostMapping("/reset-password")
    public ApiResponse<?> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        userService.resetPassword(resetPasswordDto);
        return ApiResponse.builder().message("Reset Password successfully!").build();
    }

}
