package com.example.shopeeIdentityService.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "logistics_providers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogisticsProviders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    Integer providerId;

    @Column(name = "provider_name")
    String providerName;

    @Column(name = "provider_code")
    String providerCode;

    @Column(name = "tracking_url_template")
    String trackingUrlTemplate;

    @Column(name = "contact_phone")
    String contactPhone;

    @Column(name = "is_active")
    Boolean isActive;

    @Column(name = "created_at")
    LocalDateTime createdAt;

}
