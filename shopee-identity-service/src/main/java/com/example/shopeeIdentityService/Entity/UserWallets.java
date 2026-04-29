package com.example.shopeeIdentityService.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "user_wallets")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserWallets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    Integer walletId;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "balance")
    BigDecimal balance;

    @Column(name = "coin_balance")
    Integer coinBalance;

    @Column(name = "pending_balance")
    BigDecimal pendingBalance;

    @Column(name = "currency")
    String currency;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
