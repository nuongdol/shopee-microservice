package com.example.shopeeIdentityService.Repository;

import com.example.shopeeIdentityService.Entity.RBAC_ABAC.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

    Optional<Roles> findByRoleName(String buyer);
}
