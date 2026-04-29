package com.example.shopeeIdentityService.Security.User;

import com.example.shopeeIdentityService.Entity.RBAC_ABAC.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserPrincipal extends Users implements OAuth2User {

    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public UserPrincipal(Integer id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super(id, email, password);
        this.authorities = authorities;
    }

    public static UserPrincipal create(Users users) {
        List<GrantedAuthority> authorityList = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new UserPrincipal(
                users.getUserId(),
                users.getEmail(),
                users.getPassword(),
                authorityList
        );
    }

    public static UserPrincipal create(Users user, Map<String, Object> attributes){
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return super.getUsername();
    }
}
