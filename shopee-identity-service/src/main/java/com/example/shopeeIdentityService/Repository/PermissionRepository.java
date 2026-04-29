package com.example.shopeeIdentityService.Repository;

import com.example.shopeeIdentityService.Entity.RBAC_ABAC.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, Integer> {
}
