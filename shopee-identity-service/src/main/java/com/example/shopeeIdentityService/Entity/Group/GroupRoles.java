package com.example.shopeeIdentityService.Entity.Group;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "group_roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_role_id")
    Integer groupRoleId;

    @Column(name = "group_id")
    Integer groupId;

    @Column(name = "role_id")
    Integer roleId;

    @Column(name = "assignment_type")
    Integer assignmentType;

    @Column(name = "effective_from")
    LocalDateTime effectiveFrom;

    @Column(name = "effective_to")
    LocalDateTime effectiveTo;

    @Column(name = "assigned_at")
    LocalDateTime assignedAt;

    @Column(name = "assigned_by")
    Integer assignedBy;

    @Column(name = "removed_at")
    LocalDateTime removedAt;

    @Column(name = "removed_by")
    Integer removedBy;

}
