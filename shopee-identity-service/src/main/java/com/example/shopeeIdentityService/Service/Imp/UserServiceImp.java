package com.example.shopeeIdentityService.Service.Imp;

import com.example.shopeeIdentityService.Dto.ResetPasswordDto;
import com.example.shopeeIdentityService.Entity.RBAC_ABAC.Users;
import com.example.shopeeIdentityService.Dto.Record.MailBody;
import com.example.shopeeIdentityService.Enum.Exception.ErrorCode;
import com.example.shopeeIdentityService.Exception.AppException;
import com.example.shopeeIdentityService.Repository.UserRepository;
import com.example.shopeeIdentityService.Service.EmailService;
import com.example.shopeeIdentityService.Service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final RedisService redisService;
    private static final long OTP_EXPIRY_MINUTES = 10;
    private static final String OTP_PREFIX = "otp:forgot:";
    private final PasswordEncoder passwordEncoder;

    @Override
    public void verifyMail(String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        String otp = otpGenerator();
        MailBody mailBody = MailBody.builder()
                .to(email)
                .text("This  the OTP for your forgot password request: " + otp)
                .subject("OTP for forgot password request")
                .build();
        //save otp in redis
        String keyOtp = OTP_PREFIX + email;
        redisService.setOtp(keyOtp, otp, OTP_EXPIRY_MINUTES, TimeUnit.MINUTES);
        //send otp for email
        try {
            emailService.sendSimpleMessage(mailBody);
        } catch (MessagingException e) {
            throw new AppException(ErrorCode.SEND_EMAIL);
        }
    }

    @Override
    public boolean verifyOtp(String otp, String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        String keyOtp = OTP_PREFIX + email;
        String storeOtp = redisService.getOtp(keyOtp);
        if (storeOtp == null) {
            throw new AppException(ErrorCode.OTP_NOT_EXISTED_OR_VERIFIED);
        }
        if (storeOtp.equals(otp)) {
            // keep a "verified" flag for a short time
            //save email in the redis with verified flag
            redisService.setVerifiedEmail("verified: " + email, "true", 15, TimeUnit.MINUTES);
            redisService.delete(keyOtp);//delete otp after verification
            return true;
        }
        return false;
    }

    @Override
    public void resetPassword(ResetPasswordDto resetPasswordDto) {
        //verified key
        String verifiedKey = "verified: " + resetPasswordDto.getEmail();
        if(!redisService.hasOtp(verifiedKey)){
            throw new AppException(ErrorCode.OTP_NOT_EXISTED_OR_VERIFIED);
        }
        Users user = userRepository.findByEmail(resetPasswordDto.getEmail())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));

        user.setPasswordHash(passwordEncoder.encode(resetPasswordDto.getNewPassword()));
        userRepository.save(user);
        redisService.delete(verifiedKey);
    }

    private String otpGenerator() {
        SecureRandom secureRandom = new SecureRandom();
        int otp = 100000 + secureRandom.nextInt(900000);
        return String.valueOf(otp);
    }
}
