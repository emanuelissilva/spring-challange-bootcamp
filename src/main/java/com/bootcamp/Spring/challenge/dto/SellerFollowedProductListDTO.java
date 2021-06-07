package com.bootcamp.Spring.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SellerFollowedProductListDTO {
    private long id;
    private String sellerName;
    private List<ProductDTO> products = new ArrayList<>();

    public void setProducts(List<ProductDTO> products) {
        this.products.addAll(products);
    }
}
