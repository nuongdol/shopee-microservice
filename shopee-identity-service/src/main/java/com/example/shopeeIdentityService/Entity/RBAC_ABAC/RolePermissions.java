package com.example.shopeeIdentityService.Entity.RBAC_ABAC;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role_permissions")
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RolePermissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_permission_id")
    Integer rolePermissionId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Roles roles;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    Permissions permissions;
}
