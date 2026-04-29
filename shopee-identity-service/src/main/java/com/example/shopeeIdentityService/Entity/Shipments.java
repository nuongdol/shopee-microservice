package com.example.shopeeIdentityService.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "shipments")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Shipments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id")
    Integer shipmentId;

    @Column(name = "provider_id")
    Integer providerId;

    @Column(name = "tracking_number")
    String trackingNumber;

    @Column(name = "shipping_label_url")
    String shippingLabelUrl;

    @Column(name = "shipment_weight_kg")
    Float shipmentWeightKg;

    @Column(name = "shipping_cost")
    Float shippingCost;

    @Column(name = "estimated_delivery_date")
    LocalDateTime estimatedDeliveryDate;

    @Column(name = "actual_delivery_date")
    LocalDateTime actualDeliveryDate;

    @Column(name = "pickup_date")
    LocalDateTime pickupDate;

    @Column(name = "status")
    Integer statusShipments;

    @Column(name = "notes")
    String notes;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

}
