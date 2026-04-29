package com.example.shopeeIdentityService.Entity.Group;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "scope")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Scope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scope_id")
    Integer scopeId;

    @Column(name = "scope_code")
    String scopeCode;

    @Column(name = "scope_description")
    String  scopeDescription;

    @Column(name = "scope_type")
    Integer scopeType;

    @Column(name = "parent_scope_id")
    Integer parentScopeId;

    @Column(name = "scope_level")
    Integer scopeLevel;

    @Column(name = "scope_path")
    String scopePath;

    @Column(name = "metadata")
    String metadata;

    @Column(name = "is_active")
    Boolean isActive;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updatedAt")
    LocalDateTime updatedAt;
}
