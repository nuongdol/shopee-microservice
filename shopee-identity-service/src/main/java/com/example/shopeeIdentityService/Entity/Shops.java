package com.example.shopeeIdentityService.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Entity
@Table(name = "shops")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Shops {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    Integer shopId;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "shop_name")
    String shopName;

    @Column(name = "shop_description")
    String shopDescription;

    @Column(name = "shop_logo_url")
    String shopLogoUrl;

    @Column(name = "shop_banner_url")
    String shopBannerUrl;

    @Column(name = "contact_email")
    String contactEmail;

    @Column(name = "contact_phone")
    String contactPhone;

    @Column(name = "business")
    String businessRegistrationNo;

    @Column(name = "rating")
    Float rating;

    @Column(name = "total_rating")
    Integer totalRating;

    @Column(name = "follower_count")
    Integer followerCount;

    @Column(name = "is_official")
    Boolean isOfficial;

    @Column(name = "is_preferred")
    Boolean isPreferred;

    @Column(name = "status")
    Integer status;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
