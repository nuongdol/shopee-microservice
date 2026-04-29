package com.example.shopeeIdentityService.Repository;

import com.example.shopeeIdentityService.Entity.RBAC_ABAC.RolePermissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissions, Integer> {
}
