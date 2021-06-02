package com.bootcamp.Spring.challenge.service.impl;

import com.bootcamp.Spring.challenge.dto.*;
import com.bootcamp.Spring.challenge.model.Product;
import com.bootcamp.Spring.challenge.model.ProductDetail;
import com.bootcamp.Spring.challenge.model.Seller;
import com.bootcamp.Spring.challenge.model.User;
import com.bootcamp.Spring.challenge.repositories.SellerRepository;
import com.bootcamp.Spring.challenge.service.SellerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SellerServiceImpl implements SellerService {

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

    @Override
    public SellerProductListDTO getProductlist(Integer idSeller) {
        Seller seller = sellerRepository.getOne(idSeller);
        return mapEntityProductToProductListDTO(seller);
    }


    private SellerDTO mapEntityToDTO(Seller seller) {
        SellerDTO responseDTO = new SellerDTO();
        responseDTO.setSellerName(seller.getSellerName());
        responseDTO.setSellerId(seller.getSellerId());
        responseDTO.setCountFollowers(seller.getCountFollowers());
        responseDTO.setFollowers(mapFollowers(seller.getFollowers()));
        return responseDTO;
    }

    private SellerProductListDTO mapEntityProductToProductListDTO(Seller seller) {
        SellerProductListDTO responseProductListDTO = new SellerProductListDTO();
        List<ProductDTO> list = new ArrayList<>();
        list.add(mapProductToProductDTO(seller.getProducts()));
        responseProductListDTO.setProducts(list);
        return responseProductListDTO;
    }

    private ProductDTO mapProductToProductDTO(List<Product> product) {
        ProductDTO productDTO = new ProductDTO();
        Set<ProductDetail> list = new HashSet<>();
        ProductDetail detail = new ProductDetail();
        product.forEach(product1 -> {
            detail.setProductId(product1.getProductId());
            detail.setProductName(product1.getProductName());
            detail.setProductBrand(product1.getProductBrand());
            detail.setProductType(product1.getProductType());
            detail.setProductColor(product1.getProductColor());
            detail.setProductNotes(product1.getProductNotes());
        });
        product.forEach(product1 -> {
            productDTO.setPostDate(product1.getPostDate());
            productDTO.setPostId(product1.getPostId());
            productDTO.setCategory(product1.getCategory());
            productDTO.setPrice(product1.getPrice());
            productDTO.setSellerId(product1.getSeller().getSellerId());
        });
        list.add(detail);
        productDTO.setDetail(list);
        return productDTO;
    }


    private CountFollowsDTO mapToDTO(Seller seller) {
        CountFollowsDTO responseDTO = new CountFollowsDTO();
        responseDTO.setSellerName(seller.getSellerName());
        responseDTO.setId(seller.getSellerId());
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
