package com.example.shopeeIdentityService.Security.Oauth2;

import com.example.shopeeIdentityService.Config.AppProperties;
import com.example.shopeeIdentityService.Entity.RBAC_ABAC.Users;
import com.example.shopeeIdentityService.Enum.Exception.ErrorCode;
import com.example.shopeeIdentityService.Exception.AppException;
import com.example.shopeeIdentityService.Util.CookieUtils;
import com.example.shopeeIdentityService.Util.JwtUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Oauth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    /*
    spring gọi sau khi user login Google (OAuth2) thành công.
     */

    private final JwtUtils jwtUtils ;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    private final AppProperties appProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        String targetUrl = determineTargetUrl(request, response, authentication);
        //response đã được gửi về client rồi (đã commit)
        if(response.isCommitted()){
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        /*
        xoá: session auth tạm, xoá cookie OAuth2
         */
        clearAuthenticationAttributes(request, response);
        //http://localhost:3000/oauth2/redirect?token=abcxyz
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    //xây dựng Url redirect + gắn jwt
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        /*
        - lấy redirectUri từ cookie
        - khi bắt đầu login OAuth2, lưu: redirect_uri=http://localhost:3000/oath2/redirect
         */
        Optional<String> redirectUri = CookieUtils.getCookie(request, HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie :: getValue);
        //validate redirectUri
        if(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())){
            throw new AppException(ErrorCode.UNAUTHORIZED_REDIRECT_URI);
        }

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
        Users user = (Users) authentication.getPrincipal();
        String token = jwtUtils.generateToken(user);
        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", token)
                .build().toUriString();
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);
        return appProperties.getOauth2().getAuthorizedRedirectUris()
                .stream()
                .anyMatch(authorizedRedirectUri -> {
                    // Only validate host and port. Let the clients use different paths if they want to
                    URI authorizedURI = URI.create(authorizedRedirectUri);
                    if(authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                            && authorizedURI.getPort() == clientRedirectUri.getPort()) {
                        return true;
                    }
                    return false;
                });
    }

    private void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }
}
