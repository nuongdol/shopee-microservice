package com.example.shopeeIdentityService.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    Integer categoryId;

    @Column(name = "category_name")
    String categoryName;

    @Column(name = "parent_category_id")
    Integer parentCategoryId;

    @Column(name = "category_level")
    Integer categoryLevel;

    @Column(name = "icon_url")
    String iconUrl;

    @Column(name = "is_active")
    Boolean isActive;

    @Column(name = "display_order")
    Integer displayOrder;

    @Column(name = "created_at")
    LocalDateTime createdAt;

}
