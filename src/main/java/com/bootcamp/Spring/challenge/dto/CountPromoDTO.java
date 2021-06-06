package com.bootcamp.Spring.challenge.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountPromoDTO {
    private Integer id;
    private String sellerName;
    private Integer countPromos;

    public CountPromoDTO() {
    }
}
