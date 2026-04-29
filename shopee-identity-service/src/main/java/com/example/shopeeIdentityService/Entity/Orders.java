package com.example.shopeeIdentityService.Entity;

import com.example.shopeeIdentityService.Enum.StatusCancelledBy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Integer orderId;

    @Column(name = "order_number")
    String order_number;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "shop_id")
    Integer shopId;

    @Column(name = "total_amount")
    Float totalAmount;

    @Column(name = "shipping_fee")
    Float shippingFee;

    @Column(name = "discount_amount")
    Float discountAmount;

    @Column(name = "final_amount")
    Float finalAmount;

    @Column(name = "shipping_address_id")
    Integer shippingAddressId;

    @Column(name = "shipping_method")
    String shippingMethod;

    @Column(name = "estimated_delivery_date")
    LocalDateTime estimatedDeliveryDate;

    @Column(name = "order_notes")
    String orderNotes;

    @Column(name = "status")
    Integer status;

    @Column(name = "cancellation_reason")
    String cancellationReason;

    @Column(name = "canceled_by")
    StatusCancelledBy statusCancelledBy;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
