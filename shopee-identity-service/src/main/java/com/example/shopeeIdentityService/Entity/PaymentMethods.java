package com.example.shopeeIdentityService.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Setter
@Getter
@Table(name = "payment_methods")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentMethods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "method_id")
    Integer methodId;

    @Column(name = "method_name")
    String methodName;

    @Column(name = "method_type")
    Integer methodType;

    @Column(name = "icon_url")
    String iconUrl;

    @Column(name = "is_active")
    Boolean isActive;

    @Column(name = "display_order")
    Integer displayOrder;
}
