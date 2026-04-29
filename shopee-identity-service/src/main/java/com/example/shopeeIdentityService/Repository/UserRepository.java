package com.example.shopeeIdentityService.Repository;

import com.example.shopeeIdentityService.Entity.RBAC_ABAC.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {

       Optional<Users> findByEmail(String username);
}
