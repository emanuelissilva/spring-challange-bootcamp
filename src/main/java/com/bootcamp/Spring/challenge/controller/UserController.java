package com.bootcamp.Spring.challenge.controller;

import java.util.List;

import com.bootcamp.Spring.challenge.dto.FollowedInfoDTO;
import com.bootcamp.Spring.challenge.dto.UserDTO;
import com.bootcamp.Spring.challenge.dto.UserFollowedListDTO;
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
    public ResponseEntity<UserDTO> getAllUsers(@RequestBody UserDTO userDTO) {
        UserDTO std = userService.addUser(userDTO);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity follow(@PathVariable("userId") Integer userId, @PathVariable("userIdToFollow") Integer sellerId) {
       UserDTO std = userService.followSeller(userId, sellerId);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity unfollow(@PathVariable("userId") Integer userId, @PathVariable("userIdToUnfollow") Integer sellerId) {
        UserDTO std = userService.unfollowSeller(userId, sellerId);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity followed(@PathVariable("userId") Integer userId) {
        UserFollowedListDTO std = userService.getUserById(userId);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/followed/asc")
    public ResponseEntity followedAsc(@PathVariable("userId") Integer userId) {
        List<FollowedInfoDTO> std = userService.getFollowedSellerAsc(userId);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/followed/desc")
    public ResponseEntity followedDesc(@PathVariable("userId") Integer userId) {
        List<FollowedInfoDTO> std = userService.getFollowedSellerDesc(userId);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

}