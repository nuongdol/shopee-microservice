package com.example.shopeeIdentityService.Repository;

import com.example.shopeeIdentityService.Entity.RBAC_ABAC.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoles, Integer> {

}
