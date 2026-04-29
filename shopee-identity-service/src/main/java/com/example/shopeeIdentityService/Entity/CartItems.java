package com.example.shopeeIdentityService.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Entity
@Table(name = "cart_items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    Integer cartItemId;

    @Column(name = "cart_id")
    Integer cartId;

    @Column(name = "product_id")
    Integer productId;

    @Column(name = "sku_id")
    Integer skuId;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "added_at")
    LocalDateTime addedAt;

}
