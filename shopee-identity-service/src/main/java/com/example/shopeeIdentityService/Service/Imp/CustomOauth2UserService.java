package com.example.shopeeIdentityService.Service.Imp;

import com.example.shopeeIdentityService.Dto.OAuth2UserInfo;
import com.example.shopeeIdentityService.Dto.OAuth2UserInfoFactory;
import com.example.shopeeIdentityService.Entity.RBAC_ABAC.Users;
import com.example.shopeeIdentityService.Enum.AuthProviderStatus;
import com.example.shopeeIdentityService.Enum.Exception.ErrorCode;
import com.example.shopeeIdentityService.Exception.AppException;
import com.example.shopeeIdentityService.Repository.UserRepository;
import com.example.shopeeIdentityService.Security.User.UserPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;


/*
get userDetail from the OAuth provider : google
 */
@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration()
                .getRegistrationId(), oAuth2User.getAttributes());
        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_OAUTH2_AUTHENTICATION_PROCESSING);
        }
        Optional<Users> usersOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        Users user = new Users();
        if (usersOptional.isPresent()) {
            user = usersOptional.get();
            AuthProviderStatus provider = AuthProviderStatus.fromvalue(user.getAuthProviderStatus());
            AuthProviderStatus authProviderStatus = AuthProviderStatus
                    .valueOf(oAuth2UserRequest
                            .getClientRegistration()
                            .getRegistrationId()
                            .toUpperCase());
            if (!user.getAuthProviderStatus().equals(authProviderStatus.getAuthProviderStatus())){
                throw new OAuth2AuthenticationException("Looks like you 're sign up with " +
                       provider.name() + "account. Please use your "
                        + provider.name() + "account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        }else{
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }
        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private Users registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        Users user = new Users();
        //convert Google-> AuthProviderStatus.GOOGLE-> number(2)
        AuthProviderStatus providerStatus = AuthProviderStatus.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId());
        user.setAuthProviderStatus(providerStatus.getAuthProviderStatus());
        user.setProviderId(oAuth2UserInfo.getId());
        user.setUsername(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        return userRepository.save(user);
    }

    private Users updateExistingUser(Users existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setUsername(oAuth2UserInfo.getName());
        return userRepository.save(existingUser);
    }

}
