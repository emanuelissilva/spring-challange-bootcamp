package com.bootcamp.Spring.challenge.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetail {
    private Integer productId;
    private String productName;
    private String productType;
    private String productBrand;
    private String productColor;
    private String productNotes;

    public ProductDetail(Integer productId, String productName, String productType, String productBrand, String productColor, String productNotes) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.productBrand = productBrand;
        this.productColor = productColor;
        this.productNotes = productNotes;
    }

    public ProductDetail() {
    }
}
