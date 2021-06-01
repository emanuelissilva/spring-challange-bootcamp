package com.bootcamp.Spring.challenge.controller;

import com.bootcamp.Spring.challenge.dto.CountFollowsDTO;
import com.bootcamp.Spring.challenge.dto.SellerDTO;
import com.bootcamp.Spring.challenge.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<SellerDTO> getFollowers(@PathVariable("userId") Integer sellerId) {
        SellerDTO seller = sellerService.getFollowersList(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }



}