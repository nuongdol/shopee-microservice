package com.example.shopeeIdentityService.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_sku")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sku_id")
    Integer skuId;

    @Column(name = "product_id")
    Integer productId;

    @Column(name = "sku_code")
    String skuCode;

    @Column(name = "combination")
    String combination;

    @Column(name = "original_price")
    BigDecimal originalPrice;

    @Column(name = "sale_price")
    BigDecimal salePrice;

    @Column(name = "stock_quantity")
    Integer stockQuantity;


    @Column(name = "reserved_quantity")
    Integer reservedQuantity;

    @Column(name = "sold_quantity")
    Integer soldQuantity;

    @Column(name = "images_urls")
    String imagesUrls;

    @Column(name = "barcode")
    String barcode;

    @Column(name = "is_active")
    Boolean isActive;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

}
