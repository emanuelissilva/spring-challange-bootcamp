package com.bootcamp.Spring.challenge.dto;

import com.bootcamp.Spring.challenge.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SellerProductListDTO {
    private List<ProductDTO> products = new ArrayList<>();
}
