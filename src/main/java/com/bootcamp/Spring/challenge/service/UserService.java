package com.bootcamp.Spring.challenge.service;

import com.bootcamp.Spring.challenge.dto.SellerFollowedProductListDTO;
import com.bootcamp.Spring.challenge.dto.UserDTO;
import com.bootcamp.Spring.challenge.dto.UserFollowedListDTO;
import com.bootcamp.Spring.challenge.dto.UserFollowedProductListDTO;

import java.util.List;

public interface UserService {
    public UserDTO addUser(UserDTO userDto);
    public List<UserDTO> getAllUsers();
    public UserDTO followSeller(Integer idUser, Integer idSeller);
    public UserDTO unfollowSeller(Integer userId, Integer sellerId);
    public UserFollowedListDTO getUserById(Integer idUser, String order);
    public UserFollowedProductListDTO getProductList(Integer userId, String order);
}
