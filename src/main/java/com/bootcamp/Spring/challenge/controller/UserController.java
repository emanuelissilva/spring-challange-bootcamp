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

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity follow(@PathVariable("userId") Integer userId, @PathVariable("userIdToFollow") Integer sellerId) {
       UserDTO std = userService.followSeller(userId, sellerId);
       return new ResponseEntity<>("The user"+userId+" has followed seller"+sellerId+"!", HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity unfollow(@PathVariable("userId") Integer userId, @PathVariable("userIdToUnfollow") Integer sellerId) {
        UserDTO std = userService.unfollowSeller(userId, sellerId);
        return new ResponseEntity<>("The seller"+userId+" has unfollowed seller"+sellerId+"!", HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity followed(@PathVariable("userId") Integer userId,
                                   @RequestParam(name = "order",
                                           required = false,
                                           defaultValue = "")
                                           String order){
        UserFollowedListDTO std = userService.getUserById(userId, order);
        return new ResponseEntity<>(std, HttpStatus.OK);
    }


    @GetMapping("/products/followed/{userId}/list")
    public ResponseEntity<UserFollowedProductListDTO> getProducts(@PathVariable("userId") Integer sellerId,
                                                                  @RequestParam(name = "order",
                                                                  required = false,
                                                                  defaultValue = "")
                                                                  String order){
        UserFollowedProductListDTO seller = userService.getProductList(sellerId, order);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

}