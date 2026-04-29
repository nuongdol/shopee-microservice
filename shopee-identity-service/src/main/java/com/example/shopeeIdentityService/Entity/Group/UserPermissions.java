package com.example.shopeeIdentityService.Entity.Group;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_permissions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserPermissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_permission_id")
    Integer userPermissionId;

    @Column(name = "permission_type")
    Integer permissionType;

    @Column(name = "conditions")
    String conditions;

    @Column(name = "condition_expression")
    Integer conditionExpression;

    @Column(name = "specific_resource_id")
    String specificResourceId;

    @Column(name = "effective_from")
    LocalDateTime effectiveFrom;

    @Column(name = "effective_to")
    LocalDateTime effectiveTo;

    @Column(name = "priority")
    Integer priority;

    @Column(name = "reason")
    String reason;

    @Column(name = "granted_at")
    LocalDateTime grantedAt;

    @Column(name = "granted_by")
    Integer grantedBy;

    @Column(name = "removed_at")
    LocalDateTime removedAt;

    @Column(name = "removed_by")
    Integer removedBy;
}
