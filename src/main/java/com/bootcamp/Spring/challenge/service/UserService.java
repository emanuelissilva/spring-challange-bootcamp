package com.bootcamp.Spring.challenge.service;

import com.bootcamp.Spring.challenge.dto.UserDTO;

import java.util.List;

public interface UserService {
    public UserDTO addUser(UserDTO userDto);
    public UserDTO followSeller(Integer idUser, Integer idSeller);
    public List<UserDTO> getAllUsers();
}
