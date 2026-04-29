package com.example.shopeeIdentityService.Enum;


import lombok.Getter;

@Getter
public enum AuthProviderStatus {
    //--0 =local, 1=facebook, 2=google, 3=github
    LOCAL(0),
    FACEBOOK(1),
    GOOGLE(2),
    GITHUB(3);
    private final Integer authProviderStatus;
    AuthProviderStatus(Integer authProviderStatus){
        this.authProviderStatus = authProviderStatus;
    }

    public static AuthProviderStatus fromvalue(Integer value){
        for(AuthProviderStatus status : AuthProviderStatus.values()){
            if(status.getAuthProviderStatus().equals(value)){
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + value);
    }
}
