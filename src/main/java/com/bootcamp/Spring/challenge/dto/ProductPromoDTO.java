package com.bootcamp.Spring.challenge.dto;

import com.bootcamp.Spring.challenge.model.ProductDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ProductPromoDTO {
    private Integer sellerId;
    private Integer id_post;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    private Set<ProductDetail> detail = new HashSet<>();
    private Integer category;
    private Double price;
    private Double discount;
    private Boolean hasPromo;

    public ProductPromoDTO() {
    }
}
