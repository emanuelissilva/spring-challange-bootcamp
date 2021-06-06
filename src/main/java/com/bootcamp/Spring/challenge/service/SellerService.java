package com.bootcamp.Spring.challenge.service;

import com.bootcamp.Spring.challenge.dto.*;

import java.util.List;

public interface SellerService {
    public CountFollowsDTO countFollowers(Integer idSeller);
    public CountPromoDTO countPromo(Integer idSeller);
    public SellerDTO getFollowersList(Integer idSeller);
    public SellerProductListDTO getProductlist(Integer idSeller);
    public SellerPromoDTO getProductPromolist(Integer idSeller);
    public List<FollowerInfoDTO> getFollowersAsc(Integer idSeller);
    public List<FollowerInfoDTO> getFollowersDesc(Integer idSeller);
    public SellerProductListDTO getProductDesc(Integer sellerId);
    public SellerProductListDTO getProductAsc(Integer sellerId);

}
