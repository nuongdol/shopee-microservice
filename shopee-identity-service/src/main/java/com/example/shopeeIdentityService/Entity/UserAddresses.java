package com.example.shopeeIdentityService.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_addresses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAddresses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    Integer addressId;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "recipient_name")
    String recipientName;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "address_line1")
    String addressLine1;

    @Column(name = "address_line2")
    String addressLine2;

    @Column(name = "city")
    String city;

    @Column(name = "state")
    String state;

    @Column(name = "postal_code")
    String postalCode;

    @Column(name = "country")
    String country;

    @Column(name = "is_default")
    Boolean isDefault;


    @Column(name = "address_type")
    Integer addressType;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
