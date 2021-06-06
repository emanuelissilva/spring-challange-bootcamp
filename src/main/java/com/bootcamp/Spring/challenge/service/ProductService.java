package com.bootcamp.Spring.challenge.service;

import com.bootcamp.Spring.challenge.dto.ProductDTO;
import com.bootcamp.Spring.challenge.dto.ProductPromoDTO;

public interface ProductService {
    public ProductDTO postProduct(ProductDTO productDTO);
    public ProductPromoDTO postProductPromo(ProductPromoDTO productDTO);

}
