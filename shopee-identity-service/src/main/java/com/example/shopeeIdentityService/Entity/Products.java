package com.example.shopeeIdentityService.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    Integer productId;

    @Column(name = "shop_id")
    Integer shopId;

    @Column(name = "product_name")
    String productName;

    @Column(name = "product_description")
    String productDescription;

    @Column(name = "main_category_id")
    Integer mainCategoryId;

    @Column(name = "sub_category_id")
    Integer subCategoryId;

    @Column(name = "brand")
    String brand;

    @Column(name = "sku")
    String sku;

    @Column(name = "weight_kg")
    BigDecimal weightKg;

    @Column(name = "dimensions")
    String dimensions;

    @Column(name = "rating")
    Float rating;

    @Column(name = "total_ratings")
    Integer totalRatings;

    @Column(name = "total_sold")
    Integer totalSold;

    @Column(name = "view_count")
    Integer viewCount;

    @Column(name = "min_price")
    BigDecimal minPrice;

    @Column(name = "max_price")
    BigDecimal maxPrice;

    @Column(name = "is_pre_order")
    Boolean isPreOrder;

    @Column(name = "is_used")
    Boolean isUsed;

    @Column(name = "warranty_period_months")
    Integer warrantyPeriodMonths;

    @Column(name = "status")
    Integer status;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

}
