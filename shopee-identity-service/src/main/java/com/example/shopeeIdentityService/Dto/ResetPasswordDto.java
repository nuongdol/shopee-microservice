package com.example.shopeeIdentityService.Dto;


import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class ResetPasswordDto {
    String email;
    String newPassword;
}
