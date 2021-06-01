package com.bootcamp.Spring.challenge.service.impl;

import com.bootcamp.Spring.challenge.dto.CountFollowsDTO;
import com.bootcamp.Spring.challenge.dto.FollowerInfoDTO;
import com.bootcamp.Spring.challenge.dto.SellerDTO;
import com.bootcamp.Spring.challenge.dto.UserDTO;
import com.bootcamp.Spring.challenge.model.Seller;
import com.bootcamp.Spring.challenge.model.User;
import com.bootcamp.Spring.challenge.repositories.SellerRepository;
import com.bootcamp.Spring.challenge.repositories.UserRepository;
import com.bootcamp.Spring.challenge.service.SellerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl implements SellerService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private SellerRepository sellerRepository;

    @Transactional
    @Override
    public CountFollowsDTO countFollowers(Integer idSeller) {
        Seller seller = sellerRepository.getOne(idSeller);
        seller.setCountFollowers(seller.getFollowers().size());
        return mapToDTO(seller);
    }

    @Transactional
    @Override
    public SellerDTO getFollowersList(Integer idSeller) {
        Seller seller = sellerRepository.getOne(idSeller);
        return mapEntityToDTO(seller);
    }


    private SellerDTO mapEntityToDTO(Seller seller) {
        SellerDTO responseDTO = new SellerDTO();


        responseDTO.setSellerName(seller.getSellerName());
        responseDTO.setSellerId(seller.getId());
        responseDTO.setFollowers(mapFollowers(seller.getFollowers()));
        return responseDTO;
    }

    private CountFollowsDTO mapToDTO(Seller seller) {
        CountFollowsDTO responseDTO = new CountFollowsDTO();
        responseDTO.setSellerName(seller.getSellerName());
        responseDTO.setId(seller.getId());
        responseDTO.setCountFollowers(seller.getCountFollowers());
        return responseDTO;
    }

    private List<FollowerInfoDTO> mapFollowers(Set<User> followers){
        List<FollowerInfoDTO> list = new ArrayList<>();
        followers.forEach(follower -> {
            FollowerInfoDTO followersList = new FollowerInfoDTO();
            followersList.setUserName(follower.getUserName());
            followersList.setId(follower.getUserID());
            list.add(followersList);
        });
        return list;
    }


}
