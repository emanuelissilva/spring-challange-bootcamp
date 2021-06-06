package com.bootcamp.Spring.challenge.controller;

import com.bootcamp.Spring.challenge.dto.*;
import com.bootcamp.Spring.challenge.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class SellerController {

    private SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<CountFollowsDTO> countFollowers(@PathVariable("userId") Integer sellerId) {
        CountFollowsDTO seller = sellerService.countFollowers(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/{userId}/promo/count")
    public ResponseEntity<CountPromoDTO> countPromo(@PathVariable("userId") Integer sellerId) {
        CountPromoDTO seller = sellerService.countPromo(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<SellerDTO> getFollowers(@PathVariable("userId") Integer sellerId) {
        SellerDTO seller = sellerService.getFollowersList(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/{userId}/productpromo/list")
    public ResponseEntity<SellerPromoDTO> getProductPromo(@PathVariable("userId") Integer sellerId) {
        SellerPromoDTO seller = sellerService.getProductPromolist(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/products/followed/{userId}/list")
    public ResponseEntity<SellerProductListDTO> getProducts(@PathVariable("userId") Integer sellerId) {
        SellerProductListDTO seller = sellerService.getProductlist(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/products/followed/{userId}/desc")
    public ResponseEntity<SellerProductListDTO> getProductsDesc(@PathVariable("userId") Integer sellerId) {
        SellerProductListDTO seller = sellerService.getProductDesc(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/products/followed/{userId}/asc")
    public ResponseEntity<SellerProductListDTO> getProductsAsc(@PathVariable("userId") Integer sellerId) {
        SellerProductListDTO seller = sellerService.getProductAsc(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/{userId}/follower/asc")
    public ResponseEntity followerAsc(@PathVariable("userId") Integer sellerId) {
        List<FollowerInfoDTO> std = sellerService.getFollowersAsc(sellerId);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/follower/desc")
    public ResponseEntity followerDesc(@PathVariable("userId") Integer sellerId) {
        List<FollowerInfoDTO> std = sellerService.getFollowersDesc(sellerId);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }



}