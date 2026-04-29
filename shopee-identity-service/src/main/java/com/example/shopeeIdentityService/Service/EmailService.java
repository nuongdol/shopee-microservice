package com.example.shopeeIdentityService.Service;


import com.example.shopeeIdentityService.Dto.Record.MailBody;
import jakarta.mail.MessagingException;

public interface EmailService {

    void sendSimpleMessage(MailBody mailBody) throws MessagingException;
}
