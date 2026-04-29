package com.example.shopeeIdentityService.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_status_history")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    Integer historyId;

    @Column(name = "order_id")
    Integer orderId;

    @Column(name = "old_status")
    String oldStatus;

    @Column(name = "new_status")
    String newStatus;

    @Column(name = "changed_by")
    Integer changedBy;

    @Column(name = "notes")
    String notes;

    @Column(name = "created_at")
    LocalDateTime createdAt;
}
