package com.example.shopeeIdentityService.Entity.RBAC_ABAC;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "permissions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    Integer permissionId;

    @Column(name = "permission_code")
    String permissionCode;

    @Column(name = "permission_name")
    String permissionName;

    @Column(name = "permission_description")
    String permissionDescription;

    @Column(name = "permission_type")
    Integer permissionType;

    @Column(name = "resource_type")
    String resourceType;

    @Column(name = "resource_id")
    Integer resourceId;

    @Column(name = "action")
    String action;

    @Column(name = "scope")
    Integer scope;

    @Column(name = "constraints")
    String constraints;

    @Column(name = "is_active")
    Boolean isActive;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
