package com.bootcamp.Spring.challenge.controller;

import com.bootcamp.Spring.challenge.dto.*;
import com.bootcamp.Spring.challenge.repositories.SellerRepository;
import com.bootcamp.Spring.challenge.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SellerRepository sellerRepository;

    @GetMapping("/sellers")
    public ResponseEntity<List<SellerDTO>> getAllSellers() {
        List<SellerDTO> sellers = sellerService.getAllSellers();
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    @PostMapping("/sellers")
    public ResponseEntity<SellerNewDTO> addNewSeller(@RequestBody SellerNewDTO sellerDTO) {
        SellerNewDTO std = sellerService.addSeller(sellerDTO);
        return new ResponseEntity<>(std, HttpStatus.OK);
    }

    @PostMapping("/sellers/{sellerId}/follow/{sellerIdToFollow}")
    public ResponseEntity follow(@PathVariable("sellerId") Integer followerId, @PathVariable("sellerIdToFollow") Integer followedId) {
        if(followerId==followedId)
            return new ResponseEntity<>("A seller can't follow himself!", HttpStatus.BAD_REQUEST);
        else if ((!sellerRepository.existsById(followerId)) && (!sellerRepository.existsById(followedId)))
            return new ResponseEntity<>("Both sellers doesn't exists! Try another Id", HttpStatus.BAD_REQUEST);
        else if (!sellerRepository.existsById(followerId))
            return new ResponseEntity<>("The follower seller doesn't exists! Try another Id", HttpStatus.BAD_REQUEST);
        else if (!sellerRepository.existsById(followedId))
            return new ResponseEntity<>("The followed seller doesn't exists! Try another Id", HttpStatus.BAD_REQUEST);
        else {
            SellerFollowSellerDTO std = sellerService.followSeller(followedId, followedId);
            return new ResponseEntity<>("The seller "+followerId+" has followed seller "+followedId+"!", HttpStatus.OK);
        }
    }

    @PostMapping("/sellers/{sellerId}/unfollow/{sellerIdToUnfollow}")
    public ResponseEntity unfollow(@PathVariable("sellerId") Integer followerId, @PathVariable("sellerIdToUnfollow") Integer followedId) {
        if(followerId==followedId)
            return new ResponseEntity<>("A seller can't unfollow himself!", HttpStatus.BAD_REQUEST);
        else if ((!sellerRepository.existsById(followerId)) && (!sellerRepository.existsById(followedId)))
            return new ResponseEntity<>("Both sellers doesn't exists! Try another Id", HttpStatus.BAD_REQUEST);
        else if (!sellerRepository.existsById(followerId))
            return new ResponseEntity<>("The follower seller doesn't exists! Try another Id", HttpStatus.BAD_REQUEST);
        else if (!sellerRepository.existsById(followedId))
            return new ResponseEntity<>("The followed seller doesn't exists! Try another Id", HttpStatus.BAD_REQUEST);
        else {
            SellerFollowSellerDTO std = sellerService.unfollowSeller(followedId, followedId);
            return new ResponseEntity<>("The seller "+followerId+" has unfollowed seller "+followedId+"!", HttpStatus.OK);
        }
    }

    @GetMapping("/{sellerId}/followers/count")
    public ResponseEntity<CountFollowsDTO> countFollowers(@PathVariable("sellerId") Integer sellerId) {
        CountFollowsDTO seller = sellerService.countFollowers(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/{sellerId}/followers/list")
    public ResponseEntity<SellerDTO> getFollowers(@PathVariable("sellerId") Integer sellerId,
                                                  @RequestParam(name = "order",
                                                  required = false,
                                                  defaultValue = "")
                                                  String order){
        SellerDTO seller = sellerService.getFollowersList(sellerId, order);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/sellers/{sellerId}/followed/list")
    public ResponseEntity<SellerFollowSellerDTO> getFollowed(@PathVariable("sellerId") Integer sellerId,
                                                             @RequestParam(name = "order",
                                                             required = false,
                                                             defaultValue = "")
                                                             String order){
        SellerFollowSellerDTO seller = sellerService.getFollowedList(sellerId, order);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/sellers/products/followed/{sellerId}/list")
    public ResponseEntity<SellerFollowedProductListDTO> getProductsFollowed(@PathVariable("sellerId") Integer sellerId,
                                                                            @RequestParam(name = "order",
                                                                            required = false,
                                                                            defaultValue = "")
                                                                            String order){
        SellerFollowedProductListDTO seller = sellerService.getProductFollowedList(sellerId, order);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/products/{sellerId}/list")
    public ResponseEntity<SellerProductListDTO> getProducts(@PathVariable("sellerId") Integer sellerId) {
        SellerProductListDTO seller = sellerService.getProductlist(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/products/{sellerId}/countpromo")
    public ResponseEntity<CountPromoDTO> countPromo(@PathVariable("sellerId") Integer sellerId) {
        CountPromoDTO seller = sellerService.countPromo(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/products/{sellerId}/promo/list")
    public ResponseEntity<SellerPromoDTO> getProductPromo(@PathVariable("sellerId") Integer sellerId) {
        SellerPromoDTO seller = sellerService.getProductPromolist(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }
}