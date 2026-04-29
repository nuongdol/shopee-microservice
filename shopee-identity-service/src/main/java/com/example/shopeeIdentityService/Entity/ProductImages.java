package com.example.shopeeIdentityService.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    Integer imageId;

    @Column(name = "product_id")
    Integer productId;

    @Column(name = "sku_id")
    Integer skuId;

    @Column(name = "image_url")
    String imageUrl;

    @Column(name = "display_order")
    Integer displayOrder;

    @Column(name = "is_main")
    Boolean isMain;

    @Column(name = "created_at")
    LocalDateTime createdAt;
}
