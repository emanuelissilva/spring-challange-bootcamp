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
}
