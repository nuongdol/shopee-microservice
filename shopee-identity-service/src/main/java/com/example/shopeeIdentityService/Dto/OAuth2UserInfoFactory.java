package com.example.shopeeIdentityService.Dto;

import com.example.shopeeIdentityService.Enum.AuthProviderStatus;
import com.example.shopeeIdentityService.Enum.Exception.ErrorCode;
import com.example.shopeeIdentityService.Exception.AppException;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(AuthProviderStatus.GOOGLE.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new AppException(ErrorCode.OAUTH2_AUTHENTICATION_PROCESSING);
        }
    }
}
