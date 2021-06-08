package com.bootcamp.Spring.challenge.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountPromoDTO {
    private Integer sellerId;
    private String sellerName;
    private Integer promoproducts_count;

    public CountPromoDTO() {
    }
}
