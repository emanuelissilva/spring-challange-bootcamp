package com.bootcamp.Spring.challenge.controller;

import java.util.List;

import com.bootcamp.Spring.challenge.dto.*;
import com.bootcamp.Spring.challenge.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> addNewUser(@RequestBody UserDTO userDTO) {
        UserDTO std = userService.addUser(userDTO);
        return new ResponseEntity<>(std, HttpStatus.OK);
    }

    @PostMapping("/{userId}/follow/{sellerIdToFollow}")
    public ResponseEntity follow(@PathVariable("userId") Integer userId, @PathVariable("sellerIdToFollow") Integer sellerId) {
       UserDTO std = userService.followSeller(userId, sellerId);
       return new ResponseEntity<>(std, HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{sellerIdToUnfollow}")
    public ResponseEntity unfollow(@PathVariable("userId") Integer userId, @PathVariable("sellerIdToUnfollow") Integer sellerId) {
        UserDTO std = userService.unfollowSeller(userId, sellerId);
        return new ResponseEntity<>(std, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity followed(@PathVariable("userId") Integer userId) {
        UserFollowedListDTO std = userService.getUserById(userId);
        return new ResponseEntity<>(std, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/asc")
    public ResponseEntity followedAsc(@PathVariable("userId") Integer userId) {
        List<FollowedInfoDTO> std = userService.getFollowedSellerAsc(userId);
        return new ResponseEntity<>(std, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/desc")
    public ResponseEntity followedDesc(@PathVariable("userId") Integer userId) {
        List<FollowedInfoDTO> std = userService.getFollowedSellerDesc(userId);
        return new ResponseEntity<>(std, HttpStatus.OK);
    }

    @GetMapping("/products/followed/{userId}/list")
    public ResponseEntity<UserFollowedProductListDTO> getProducts(@PathVariable("userId") Integer sellerId) {
        UserFollowedProductListDTO seller = userService.getProductList(sellerId);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

}