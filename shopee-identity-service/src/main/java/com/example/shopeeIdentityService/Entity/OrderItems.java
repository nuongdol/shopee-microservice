package com.example.shopeeIdentityService.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    Integer orderItemId;

    @Column(name = "order_id")
    Integer orderId;

    @Column(name = "product_id")
    Integer productid;

    @Column(name = "product_name")
    String productName;

    @Column(name = "sku_id")
    Integer skuId;

    @Column(name = "sku_code")
    String skuCode;

    @Column(name = "variant_info")
    String variantInfo;

    @Column(name = "unit_price")
    Float unitPrice;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "subtotal")
    Float subtotal;

    @Column(name = "is_reviewed")
    Boolean isReviewed;

}
