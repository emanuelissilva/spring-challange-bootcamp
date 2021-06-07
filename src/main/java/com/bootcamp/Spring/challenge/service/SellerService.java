package com.bootcamp.Spring.challenge.service;

import com.bootcamp.Spring.challenge.dto.*;

import java.util.List;

public interface SellerService {
    public SellerDTO addSeller(SellerDTO sellerDto);
    public List<SellerDTO> getAllSellers();
    public SellerFollowSellerDTO followSeller(Integer idFollowSeller, Integer idSeller);
    public SellerFollowSellerDTO unfollowSeller(Integer idFollowSeller, Integer sellerId);
    public CountFollowsDTO countFollowers(Integer idSeller);
    public CountPromoDTO countPromo(Integer idSeller);
    public SellerDTO getFollowersList(Integer idSeller);
    public SellerFollowSellerDTO getFollowedList(Integer idSeller);
    public SellerProductListDTO getProductlist(Integer idSeller);
    public SellerFollowedProductListDTO getProductFollowedList(Integer idSeller);
    public SellerPromoDTO getProductPromolist(Integer idSeller);
    public SellerDTO getFollowersAsc(Integer idSeller);
    public SellerDTO getFollowersDesc(Integer idSeller);
    public SellerFollowSellerDTO getFollowedAsc(Integer idSeller);
    public SellerFollowSellerDTO getFollowedDesc(Integer idSeller);
    public SellerProductListDTO getProductDesc(Integer sellerId);
    public SellerProductListDTO getProductAsc(Integer sellerId);
}
