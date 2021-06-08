package com.bootcamp.Spring.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SellerProductPromoDTO {
    private List<ProductPromoDTO> products = new ArrayList<>();

}
