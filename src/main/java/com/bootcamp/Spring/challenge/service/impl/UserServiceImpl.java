package com.bootcamp.Spring.challenge.service.impl;

import com.bootcamp.Spring.challenge.dto.*;
import com.bootcamp.Spring.challenge.model.Seller;
import com.bootcamp.Spring.challenge.model.User;
import com.bootcamp.Spring.challenge.repositories.SellerRepository;
import com.bootcamp.Spring.challenge.repositories.UserRepository;
import com.bootcamp.Spring.challenge.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private SellerRepository sellerRepository;


    @Transactional
    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = new User();
        mapDTOToEntity(userDTO, user);
        User savedUser = userRepository.save(user);
        return mapEntityToDTO(savedUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOs = new ArrayList<>();
        List<User> users = userRepository.findAll();
        users.stream().forEach(user -> {
            UserDTO userDTO = mapEntityToDTO(user);
            userDTOs.add(userDTO);
        });
        return userDTOs;
    }

    @Transactional
    @Override
    public UserDTO followSeller(Integer idUser, Integer idSeller) {
        User user = userRepository.getOne(idUser);
        Seller seller = sellerRepository.getOne(idSeller);
        user.getFollowedSellers().add(seller);
        User userSaved = userRepository.save(user);
        return mapEntityToDTO(user);
    }

    @Transactional
    @Override
    public UserFollowedListDTO getUserById(Integer idUser) {
        User user = userRepository.getOne(idUser);
        return mapUserFollowedListToDTO(user);
    }

    private void mapDTOToEntity(UserDTO userDTO, User user) {
        user.setUserName(userDTO.getUserName());
        if (null == user.getFollowedSellers()) {
            user.setFollowedSellers(new HashSet<>());
        }
        userDTO.getFollowedSellers().stream().forEach(followedSeller -> {
            Seller seller = sellerRepository.findBySellerName(followedSeller);
            if (null == seller) {
                seller = new Seller();
                seller.setSellerName(new String());
            }
            seller.setSellerName(followedSeller);
            user.followSeller(seller);
        });
    }

    private UserDTO mapEntityToDTO(User user) {
        UserDTO responseDTO = new UserDTO();
        responseDTO.setUserName(user.getUserName());
        responseDTO.setId(user.getUserID());
        responseDTO.setFollowedSellers(user.getFollowedSellers()
                .stream()
                .map(Seller::getSellerName)
                .collect(Collectors.toList()));
        return responseDTO;
    }

    private UserFollowedListDTO mapUserFollowedListToDTO(User user) {
        UserFollowedListDTO responseDTO = new UserFollowedListDTO();
        responseDTO.setUserName(user.getUserName());
        responseDTO.setId(user.getUserID());
        responseDTO.setFollowedSellers(mapFollowed(user.getFollowedSellers()));
        return responseDTO;
    }

    private List<FollowedInfoDTO> mapFollowed(Set<Seller> followedList){
        List<FollowedInfoDTO> list = new ArrayList<>();
        followedList.forEach(followed -> {
            FollowedInfoDTO followedListFinal = new FollowedInfoDTO();
            followedListFinal.setUserName(followed.getSellerName());
            followedListFinal.setId(followed.getSellerId());
            list.add(followedListFinal);
        });
        return list;
    }
}
