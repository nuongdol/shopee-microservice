package com.example.shopeeIdentityService.Entity.RBAC_ABAC;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "policies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Policies {
    @Column(name = "policies_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer policiesId;

    @Column(name = "role_id")
    Integer roleId;

    @Column(name = "permission_key")
    String permissionKey;

    @Column(name = "condition_spel")
    String conditionSpel;

    @Column(name = "description")
    String description;

}
