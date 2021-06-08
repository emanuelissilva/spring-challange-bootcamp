package com.bootcamp.Spring.challenge.controller;

import java.util.List;

import com.bootcamp.Spring.challenge.dto.*;
import com.bootcamp.Spring.challenge.model.Seller;
import com.bootcamp.Spring.challenge.model.User;
import com.bootcamp.Spring.challenge.repositories.SellerRepository;
import com.bootcamp.Spring.challenge.repositories.UserRepository;
import com.bootcamp.Spring.challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserNewDTO> addNewUser(@RequestBody UserNewDTO userDTO) {
        UserNewDTO std = userService.addUser(userDTO);
        return new ResponseEntity<>(std, HttpStatus.OK);
    }

    @PostMapping("/{userId}/follow/{sellerIdToFollow}")
    public ResponseEntity follow(@PathVariable("userId") Integer userId, @PathVariable("sellerIdToFollow") Integer sellerId) {
        User followed = userRepository.getById(userId);
        Seller follower = sellerRepository.getById(sellerId);
        if(followed.getFollowedSellers().contains(follower)){
            return new ResponseEntity<>("This seller is already been followed!", HttpStatus.BAD_REQUEST);
        }else if ((!userRepository.existsById(userId)) && (!sellerRepository.existsById(sellerId)))
            return new ResponseEntity<>("Both seller and user doesn't exists! Try another Id", HttpStatus.BAD_REQUEST);
        else if (!sellerRepository.existsById(sellerId))
            return new ResponseEntity<>("This seller doesn't exists! Try another Id", HttpStatus.BAD_REQUEST);
        else if (!userRepository.existsById(userId))
            return new ResponseEntity<>("This user doesn't exists! Try another Id", HttpStatus.BAD_REQUEST);
        else {
            UserDTO std = userService.followSeller(userId, sellerId);
            return new ResponseEntity<>("The user " + userId + " has followed seller " + sellerId + "!", HttpStatus.OK);
        }
    }

    @PostMapping("/{userId}/unfollow/{sellerIdToUnfollow}")
    public ResponseEntity unfollow(@PathVariable("userId") Integer userId, @PathVariable("sellerIdToUnfollow") Integer sellerId) {
        User followed = userRepository.getById(userId);
        Seller follower = sellerRepository.getById(sellerId);
        if(!followed.getFollowedSellers().contains(follower)){
            return new ResponseEntity<>("It's not possible unfollow a seller that isn't been followed!", HttpStatus.BAD_REQUEST);
        }else if ((!userRepository.existsById(userId)) && (!sellerRepository.existsById(sellerId)))
            return new ResponseEntity<>("Both seller and user doesn't exists! Try another Id", HttpStatus.BAD_REQUEST);
        else if (!sellerRepository.existsById(sellerId))
            return new ResponseEntity<>("This seller doesn't exists! Try another Id", HttpStatus.BAD_REQUEST);
        else if (!userRepository.existsById(userId))
            return new ResponseEntity<>("This user doesn't exists! Try another Id", HttpStatus.BAD_REQUEST);
        else {
            UserDTO std = userService.unfollowSeller(userId, sellerId);
            return new ResponseEntity<>("The user "+userId+" has unfollowed seller "+sellerId+"!", HttpStatus.OK);
        }
    }

    @GetMapping("/{sellerId}/followed/list")
    public ResponseEntity followed(@PathVariable("sellerId") Integer userId,
                                   @RequestParam(name = "order",
                                           required = false,
                                           defaultValue = "")
                                           String order){
        UserFollowedListDTO std = userService.getUserById(userId, order);
        return new ResponseEntity<>(std, HttpStatus.OK);
    }


    @GetMapping("/products/followed/{sellerId}/list")
    public ResponseEntity<UserFollowedProductListDTO> getProducts(@PathVariable("sellerId") Integer sellerId,
                                                                  @RequestParam(name = "order",
                                                                  required = false,
                                                                  defaultValue = "")
                                                                  String order){
        UserFollowedProductListDTO seller = userService.getProductList(sellerId, order);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

}