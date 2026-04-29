package com.example.shopeeIdentityService.Entity.Group;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "group_members")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupMembers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_member_id")
    Integer groupMemberId;

    @Column(name = "group_id")
    Integer groupId;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "member_role")
    Integer memberRole;

    @Column(name = "joined_at")
    LocalDateTime joinedAt;

    @Column(name = "invited_by")
    Integer invitedBy;

    @Column(name = "invitation_accepted_at")
    LocalDateTime invitationAcceptedAt;

    @Column(name = "membership_status")
    Integer membershipStatus;

    @Column(name = "added_at")
    LocalDateTime  addedAt;

    @Column(name = "added_by")
    Integer addedBy;

    @Column(name = "removed_at")
    LocalDateTime removedAt;

    @Column(name = "removed_by")
    Integer removedBy;

}
