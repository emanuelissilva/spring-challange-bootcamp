package com.bootcamp.Spring.challenge.dto;

import com.bootcamp.Spring.challenge.model.ProductDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
public class ProductDTO {
    private Integer sellerId;
    private Integer postId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate postDate;
    private Set<ProductDetail> detail = new HashSet<>();
    private Integer category;
    private Double price;
    public ProductDTO() {
    }
}
