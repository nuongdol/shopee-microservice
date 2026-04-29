package com.example.shopeeIdentityService.Entity.Group;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_groups")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    Integer groupId;

    @Column(name = "group_code")
    String groupCode;

    @Column(name = "group_name")
    String groupName;

    @Column(name = "group_description")
    String groupDescription;

    @Column(name = "group_type")
    Integer groupType;

    @Column(name = "parent_group_id")
    Integer parentGroupId;

    @Column(name = "group_level")
    Integer groupLevel;

    @Column(name = "groupPath")
    String groupPath;

    @Column(name = "scope_id")
    Integer scopeId;

    @Column(name = "scope_type")
    String scopeType;

    @Column(name = "is_active")
    Boolean isActive;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Column(name = "created_by")
    Integer createdBy;

}
