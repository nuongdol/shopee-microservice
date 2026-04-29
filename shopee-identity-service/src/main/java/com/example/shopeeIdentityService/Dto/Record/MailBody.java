package com.example.shopeeIdentityService.Dto.Record;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String text) {
}
