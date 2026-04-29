package com.example.shopeeIdentityService.Entity.RBAC_ABAC;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Integer userId;

    @Column(name = "username")
    @NotBlank(message = "The username field can't be blank")
    String username;

    @Column(name = "email")
    @NotBlank(message = "The email field can't be blank")
    @Email(message = "Please enter email in proper format!")
    String email;

    @Column(name = "password_hash")
    @NotBlank(message = "The password field can't be blank")
    @Size(min = 5, message = "The password must have at least 5 characters")
    String passwordHash;

    @Column(name = "full_name")
    @NotBlank(message = "The full name field can't be blank")
    String fullName;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Column(name = "gender")
    Integer gender;

    @Column(name = "profile_picture_url")
    String profilePictureUrl;

    @Column(name = "is_seller")
    Boolean isSeller;

    @Column(name = "is_verified")
    Boolean isVerified;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Column(name = "last_login")
    LocalDateTime lastLogin;

    @Column(name = "status")
    Integer status;

    @Column(name = "auth_provider_status")
    Integer authProviderStatus;

    @Column(name = "provider_id")
    String providerId;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    Set<UserRoles> userRoles;

    public Users(Integer id, String email, String password) {
        this.userId = id;
        this.email = email;
        this.passwordHash = password;
    }

    // grantedAuthorities: các role của user vào UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (UserRoles userRole : userRoles) {
            //add roles
            String roleName = userRole.getRoles().getRoleName();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));

            //add permission (get role -> permission)
            if (!userRole.getRoles().getRolePermissions().isEmpty()) {
                for (RolePermissions rp : userRole.getRoles().getRolePermissions()) {
                    String permissionName = rp.getPermissions().getPermissionName();
                    authorities.add(new SimpleGrantedAuthority(permissionName));
                }
            }
        }
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
