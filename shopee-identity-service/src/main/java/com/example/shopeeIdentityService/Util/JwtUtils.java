package com.example.shopeeIdentityService.Util;


import com.example.shopeeIdentityService.Entity.RBAC_ABAC.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {

    private SecretKey key;

    private static final long EXPIRATION_TIME = 86400000; //24hours or 86400000 milliseconds
    private static final long REFRESHER_TIME = 604800000;

    public JwtUtils(@Value("${jwt.secret}") String secretString) {
        this.key = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
    }

    //create token
    public String generateToken(Users userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("email", userDetails.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);

    }

    public String extractEmail(String token){
        return extractClaims(token, claims -> claims.get("email", String.class));
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

    //create refresh token
    public String generateRefreshToken(Users userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + REFRESHER_TIME);
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("email", userDetails.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

}
