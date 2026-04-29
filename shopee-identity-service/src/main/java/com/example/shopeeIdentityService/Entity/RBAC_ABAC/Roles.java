package com.example.shopeeIdentityService.Entity.RBAC_ABAC;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    Integer roleId;

    @Column(name = "role_code")
    String roleCode;

    @Column(name = "role_name")
    String roleName;

    @Column(name = "role_description")
    String roleDescription;

    @Column(name = "role_type")
    Integer roleType;

    @Column(name = "parent_role_id")
    Integer parentRoleId;

    @Column(name = "role_level")
    Integer roleLevel;

    @Column(name = "role_path")
    String rolePath;

    @Column(name = "is_default")
    Integer isDefault; //1-true, 0-false

    @Column(name = "is_system_role")
    Integer isSystemRole; //1-true, 0-false

    @Column(name = "is_active")
    Integer isActive; //1-true, 0-false

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    Set<RolePermissions> rolePermissions;

}
