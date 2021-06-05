package com.bootcamp.Spring.challenge.service;

import com.bootcamp.Spring.challenge.dto.UserDTO;
import com.bootcamp.Spring.challenge.dto.UserFollowedListDTO;

import java.util.List;

public interface UserService {
    public UserDTO addUser(UserDTO userDto);
    public UserDTO followSeller(Integer idUser, Integer idSeller);
    public UserFollowedListDTO getUserById(Integer idUser);
    public UserFollowedListDTO getFollowedAsc(Integer idUser);
    public UserFollowedListDTO getFollowedDesc(Integer idUser);
    public List<UserDTO> getAllUsers();
    public UserDTO unfollowSeller(Integer userId, Integer sellerId);
}
