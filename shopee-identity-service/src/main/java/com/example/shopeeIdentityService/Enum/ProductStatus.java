package com.example.shopeeIdentityService.Enum;

import lombok.Getter;

@Getter
public enum ProductStatus {
    //-- 1=draft, 2=active, 3=out_of_stock, 4=inactive, 5=banned
    DRAFT(0),
    ACTIVE(1),
    OUT_OF_STOCK(2),
    INACTIVE(3),
    BANNED(4);
    private final Integer statusProduct;

    ProductStatus(Integer statusProduct) {
        this.statusProduct = statusProduct;
    }

    public static ProductStatus fromValue(Integer value){
        for(ProductStatus status : ProductStatus.values()){
            if(status.getStatusProduct().equals(value)){
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + value);
    }
}
