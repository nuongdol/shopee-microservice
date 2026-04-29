package com.example.shopeeIdentityService.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Entity
@Table(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    Integer paymentId;

    @Column(name = "order_id")
    Integer orderId;

    @Column(name = "payment_number")
    String paymentNumber;

    @Column(name = "payment_method_id")
    Integer paymentMethodId;

    @Column(name = "amount")
    Float amount;

    @Column(name = "currency")
    String currency;

    @Column(name = "payment_status")
    Integer paymentStatus;

    @Column(name = "transaction_id")
    String transactionId;

    @Column(name = "payer_user_id")
    Integer payerUserId;

    @Column(name = "paid_at")
    LocalDateTime paidAt;

    @Column(name = "refund_amount")
    Float refundAmount;

    @Column(name = "refund_reason")
    String refundReason;

    @Column(name = "refunded_at")
    String refundedAt;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updatedAt")
    LocalDateTime updatedAt;

}
