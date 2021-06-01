package com.bootcamp.Spring.challenge.service;

import com.bootcamp.Spring.challenge.dto.CountFollowsDTO;
import com.bootcamp.Spring.challenge.dto.SellerDTO;

public interface SellerService {
    public CountFollowsDTO countFollowers(Integer idSeller);
    public SellerDTO getFollowersList(Integer idSeller);

}
