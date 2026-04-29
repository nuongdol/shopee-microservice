package com.example.shopeeIdentityService.Service.Imp;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    //Store data with a custom TTL
    public void setToken(String token, String value, long ttl, TimeUnit unit) {
        redisTemplate.opsForValue().set(token, value, ttl, unit);
    }

    //check if token exists in the database
    public boolean hasToken(String token) {
        return redisTemplate.hasKey(token);
    }

    //OTP
    public void setOtp(String email, String Opt, long ttl, TimeUnit unit){
        redisTemplate.opsForValue().set(email, Opt, ttl, unit);
    }

    public String getOtp(String keyOtp){
        return redisTemplate.opsForValue().get(keyOtp);
    }
    public void setVerifiedEmail(String email, String verified, int TTL, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(email, verified, TTL, timeUnit );
    }
    public boolean hasOtp(String verifiedOtp){
        return redisTemplate.hasKey(verifiedOtp);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}

