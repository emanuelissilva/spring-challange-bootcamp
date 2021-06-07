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

    @GetMapping("/sellers")
    public ResponseEntity<List<SellerDTO>> getAllSellers() {
        List<SellerDTO> sellers = sellerService.getAllSellers();
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    @PostMapping("/sellers")
    public ResponseEntity<SellerDTO> addNewSeller(@RequestBody SellerDTO sellerDTO) {
        SellerDTO std = sellerService.addSeller(sellerDTO);
        return new ResponseEntity<>(std, HttpStatus.OK);
    }

    @PostMapping("/sellers/{sellerId}/follow/{sellerIdToFollow}")
    public ResponseEntity follow(@PathVariable("sellerId") Integer followerId, @PathVariable("sellerIdToFollow") Integer followedId) {
        SellerFollowSellerDTO std = sellerService.followSeller(followerId, followedId);
        return new ResponseEntity<>("The seller"+followerId+" has followed seller"+followedId+"!", HttpStatus.OK);
    }

    @PostMapping("/sellers/{sellerId}/unfollow/{sellerIdToUnfollow}")
    public ResponseEntity unfollow(@PathVariable("sellerId") Integer followerId, @PathVariable("sellerIdToUnfollow") Integer followedId) {
        SellerFollowSellerDTO std = sellerService.unfollowSeller(followerId, followedId);
        return new ResponseEntity<>("The seller"+followerId+" has unfollowed seller"+followedId+"!", HttpStatus.OK);
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

    @GetMapping("/sellers/{userId}/followed/list")
    public ResponseEntity<SellerFollowSellerDTO> getFollowed(@PathVariable("userId") Integer sellerId) {
        SellerFollowSellerDTO seller = sellerService.getFollowedList(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/sellers/products/followed/{userId}/list")
    public ResponseEntity<SellerFollowedProductListDTO> getProductsFollowed(@PathVariable("userId") Integer sellerId) {
        SellerFollowedProductListDTO seller = sellerService.getProductFollowedList(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/products/{userId}/list")
    public ResponseEntity<SellerProductListDTO> getProducts(@PathVariable("userId") Integer sellerId) {
        SellerProductListDTO seller = sellerService.getProductlist(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/listname_asc")
    public ResponseEntity followerAsc( //@RequestParam(value="name_asc") String param1,
                                       @PathVariable("userId") Integer sellerId) {
        SellerDTO std = sellerService.getFollowersAsc(sellerId);
        return new ResponseEntity<>(std, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/listname_desc")
    public ResponseEntity followerDesc(//@RequestParam(value="name_desc") String param1,
                                       @PathVariable("userId") Integer sellerId) {
        SellerDTO std = sellerService.getFollowersDesc(sellerId);
        return new ResponseEntity<>(std, HttpStatus.OK);
    }

    @GetMapping("/sellers/{userId}/followed/listname_asc")
    public ResponseEntity followedAsc( //@RequestParam(value="name_asc") String param1,
                                       @PathVariable("userId") Integer sellerId) {
        SellerFollowSellerDTO std = sellerService.getFollowedAsc(sellerId);
        return new ResponseEntity<>(std, HttpStatus.OK);
    }

    @GetMapping("/sellers/{userId}/followed/listname_desc")
    public ResponseEntity followedDesc( //@RequestParam(value="name_asc") String param1,
                                        @PathVariable("userId") Integer sellerId) {
        SellerFollowSellerDTO std = sellerService.getFollowedDesc(sellerId);
        return new ResponseEntity<>(std, HttpStatus.OK);
    }

    @GetMapping("/sellers/products/followed/{userId}/date_desc")
    public ResponseEntity<SellerFollowedProductListDTO> getProductsDesc(@PathVariable("userId") Integer sellerId) {
        SellerFollowedProductListDTO seller = sellerService.getProductDesc(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/sellers/products/followed/{userId}/date_asc")
    public ResponseEntity<SellerFollowedProductListDTO> getProductsAsc(@PathVariable("userId") Integer sellerId) {
        SellerFollowedProductListDTO seller= sellerService.getProductAsc(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/products/{userId}/countpromo")
    public ResponseEntity<CountPromoDTO> countPromo(@PathVariable("userId") Integer sellerId) {
        CountPromoDTO seller = sellerService.countPromo(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/products/{userId}/promo/list")
    public ResponseEntity<SellerPromoDTO> getProductPromo(@PathVariable("userId") Integer sellerId) {
        SellerPromoDTO seller = sellerService.getProductPromolist(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }
}