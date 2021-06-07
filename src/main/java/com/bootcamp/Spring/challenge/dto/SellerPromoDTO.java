package com.bootcamp.Spring.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SellerPromoDTO {
    private Integer sellerId;
    private String sellerName;
    private List<ProductPromoDTO> posts = new ArrayList<>();
}
