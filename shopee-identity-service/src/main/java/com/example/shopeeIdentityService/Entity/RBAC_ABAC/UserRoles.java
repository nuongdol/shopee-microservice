package com.example.shopeeIdentityService.Entity.RBAC_ABAC;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_roles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    Integer userRoleId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Roles roles;

    @Column(name = "assignment_type")
    Integer assignmentType;

    @Column(name = "scope_id")
    String scopeId;

    @Column(name = "scope_type")
    String scopeType;

    @Column(name = "effective_from")
    LocalDateTime effectiveFrom;

    @Column(name = "effective_to")
    LocalDateTime effectiveTo;

    @Column(name = "delegated_by")
    Integer delegatedBy;

    @Column(name = "delegated_at")
    LocalDateTime delegatedAt;

    @Column(name = "priority")
    Integer priority;

    @Column(name = "assigned_at")
    LocalDateTime assignedAt;

    @Column(name = "assigned_by")
    Integer assignedBy;

    @Column(name = "revoked_at")
    LocalDateTime revokedAt;

    @Column(name = "revoked_by")
    Integer revokedBy;

    @Column(name = "revocation_reason")
    String revocationReason;

}
