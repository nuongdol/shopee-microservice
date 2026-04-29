package com.example.shopeeIdentityService.Enum;


import lombok.Getter;


@Getter
public enum StatusGender {
    MALE(0),
    GENDER(1),
    DIFFERENT(2);
    private final Integer gender;
    StatusGender(Integer gender) {
        this.gender = gender;
    }

}
