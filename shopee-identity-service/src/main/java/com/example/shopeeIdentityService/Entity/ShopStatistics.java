package com.example.shopeeIdentityService.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "shop_statistics")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stat_id")
    Integer statId;

    @Column(name = "shop_id")
    Integer shopId;

    @Column(name = "total_products")
    Integer totalProducts;

    @Column(name = "total_sales")
    Integer totalSales;

    @Column(name = "total_revenue")
    BigDecimal totalRevenue;

    @Column(name = "daily_views")
    Integer dailyViews;

    @Column(name = "monthly_views")
    Integer monthlyViews;

    @Column(name = "last_updated_date")
    LocalDateTime lastUpdatedDate;

}
