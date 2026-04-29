package com.example.shopeeIdentityService.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserDto {

    @Size(min = 3, message = "USERNAME_INVALID")
    private String username;

    @Size(min = 8, message = "INVALID_PASSWORD")
    private String password;

    @Email
    private String email;

}
