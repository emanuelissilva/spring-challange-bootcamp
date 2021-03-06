package com.bootcamp.Spring.challenge.controller;

import com.bootcamp.Spring.challenge.dto.ProductDTO;
import com.bootcamp.Spring.challenge.dto.ProductPromoDTO;
import com.bootcamp.Spring.challenge.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products/newpost")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO std = productService.postProduct(productDTO);
        return new ResponseEntity<>("The product was posted successfully!", HttpStatus.OK);
    }

    @PostMapping("/products/newpromopost")
    public ResponseEntity<?> addProduct(@RequestBody ProductPromoDTO productDTO) {
        ProductPromoDTO std = productService.postProductPromo(productDTO);
        return new ResponseEntity<>("The product was posted successfully!", HttpStatus.OK);
    }

}
