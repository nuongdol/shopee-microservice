package com.example.shopeeIdentityService.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Table(name = "products_variants")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductsVariants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = " variant_id")
    Integer variantId;

    @Column(name = "product_id")
    Integer productId;

    @Column(name= "variant_name")
    String variantName;

    @Column(name = "variantValue")
    String variantValue;

    @Column(name = "displayOrder")
    Integer displayOrder;
}
