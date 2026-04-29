package com.example.shopeeIdentityService.Repository;


import com.example.shopeeIdentityService.Entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

}
