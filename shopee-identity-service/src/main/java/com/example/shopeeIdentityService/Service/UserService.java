package com.example.shopeeIdentityService.Service;

import com.example.shopeeIdentityService.Dto.ResetPasswordDto;

public interface UserService {

    void verifyMail(String email);

    boolean verifyOtp(String otp, String email);

    void resetPassword(ResetPasswordDto resetPasswordDto);
}
