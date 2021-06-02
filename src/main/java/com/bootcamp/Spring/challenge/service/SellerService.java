package com.bootcamp.Spring.challenge.service;

import com.bootcamp.Spring.challenge.dto.CountFollowsDTO;
import com.bootcamp.Spring.challenge.dto.SellerDTO;
import com.bootcamp.Spring.challenge.dto.SellerProductListDTO;

public interface SellerService {
    public CountFollowsDTO countFollowers(Integer idSeller);
    public SellerDTO getFollowersList(Integer idSeller);
    public SellerProductListDTO getProductlist(Integer idSeller);

}
