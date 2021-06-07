package com.bootcamp.Spring.challenge.service.impl;

import com.bootcamp.Spring.challenge.dto.*;
import com.bootcamp.Spring.challenge.model.Product;
import com.bootcamp.Spring.challenge.model.ProductDetail;
import com.bootcamp.Spring.challenge.model.Seller;
import com.bootcamp.Spring.challenge.model.User;
import com.bootcamp.Spring.challenge.repositories.SellerRepository;
import com.bootcamp.Spring.challenge.service.SellerService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;

@Service
public class SellerServiceImpl implements SellerService {

    @Resource
    private SellerRepository sellerRepository;

    @Transactional
    @Override
    public SellerDTO addSeller(SellerDTO sellerDTO) {
        Seller seller = new Seller();
        mapDTOToEntity(sellerDTO, seller);
        Seller savedSeller = sellerRepository.save(seller);
        return mapSellerEntityToSellerDTO(savedSeller);
    }

    @Override
    public List<SellerDTO> getAllSellers() {
        List<SellerDTO> sellerDTOs = new ArrayList<>();
        List<Seller> sellers = sellerRepository.findAll();
        sellers.stream().forEach(seller -> {
            SellerDTO sellerDTO = mapSellerEntityToSellerDTO(seller);
            sellerDTOs.add(sellerDTO);
        });
        return sellerDTOs;
    }

    @Transactional
    @Override
    public SellerFollowSellerDTO followSeller(Integer followedId, Integer followerId){
        Seller user = sellerRepository.getOne(followerId);
        Seller seller = sellerRepository.getOne(followedId);
        user.followSeller(seller);
        Seller sellerSaved = sellerRepository.save(user);
        return mapEntityToSellerFollowDTO(seller);
    }

    @Transactional
    @Override
    public SellerFollowSellerDTO unfollowSeller(Integer followedId, Integer followerId) {
        Seller user = sellerRepository.getOne(followedId);
        Seller seller = sellerRepository.getOne(followerId);
        user.unfollowSeller(seller);
        Seller sellerSaved = sellerRepository.save(user);
        return mapEntityToSellerFollowDTO(seller);
    }

    @Transactional
    @Override
    public CountFollowsDTO countFollowers(Integer idSeller) {
        Seller seller = sellerRepository.getOne(idSeller);
        seller.setFollowers_count(seller.getFollowers().size());
        return mapToDTO(seller);
    }

    @Transactional
    @Override
    public CountPromoDTO countPromo(Integer idSeller) {
        Seller seller = sellerRepository.getOne(idSeller);
        return mapCountProductsToDTO(seller);
    }

    @Transactional
    @Override
    public SellerDTO getFollowersList(Integer idSeller) {
        Seller seller = sellerRepository.getOne(idSeller);
        return mapSellerEntityToSellerDTO(seller);
    }

    @Transactional
    @Override
    public SellerFollowSellerDTO getFollowedList(Integer idUser) {
        Seller seller = sellerRepository.getOne(idUser);
        return mapEntityToSellerFollowDTO(seller);
    }

    @Override
    public SellerProductListDTO getProductlist(Integer idSeller) {
        Seller seller = sellerRepository.getOne(idSeller);
        return mapEntityProductToProductListDTO(seller);
    }

    @Override
    public SellerFollowedProductListDTO getProductFollowedList(Integer idSeller) {
        SellerFollowedProductListDTO user = new SellerFollowedProductListDTO();
        SellerFollowedProductListDTO user2 = new SellerFollowedProductListDTO();
        List<ProductDTO> posts = new ArrayList<>();
        Seller seller1 = sellerRepository.getOne(idSeller);
        user.setSellerName(seller1.getSellerName());
        user.setSellerId(seller1.getSellerId());
        seller1.getFollowedSellers().forEach(seller -> {
            user2.setPosts(mapProductToProductDTO(seller.getProducts()));
        });
        user2.getPosts().forEach(post -> {
            if(post.getDate().isBefore( LocalDate.now()) && post.getDate().isAfter(LocalDate.now().minusWeeks(2))){
                posts.add(post);
            }
        });
        user.setPosts(posts);
        Collections.sort(user.getPosts(), Comparator.comparing(ProductDTO::getDate).reversed());
        return user;
    }

    @Override
    public SellerPromoDTO getProductPromolist(Integer idSeller) {
        Seller seller = sellerRepository.getOne(idSeller);
        return mapEntityProductToSellerPromoDTO(seller);
    }


    @Override
    public SellerFollowedProductListDTO getProductDesc(Integer sellerId) {
        Seller response = sellerRepository.getOne(sellerId);
        SellerFollowedProductListDTO user = new SellerFollowedProductListDTO();
        user.setSellerName(response.getSellerName());
        user.setSellerId(response.getSellerId());
        response.getFollowedSellers().forEach(seller -> {
            user.setPosts(mapProductToProductDTO(seller.getProducts()));
        });
        Collections.sort(user.getPosts(), Comparator.comparing(ProductDTO::getDate).reversed());
        return user;
    }

    @Override
    public SellerFollowedProductListDTO getProductAsc(Integer sellerId) {
        Seller response = sellerRepository.getOne(sellerId);
        SellerFollowedProductListDTO user = new SellerFollowedProductListDTO();
        user.setSellerName(response.getSellerName());
        user.setSellerId(response.getSellerId());
        response.getFollowedSellers().forEach(seller -> {
            user.setPosts(mapProductToProductDTO(seller.getProducts()));
        });
        Collections.sort(user.getPosts(), Comparator.comparing(ProductDTO::getDate));
        return user;
    }

    @Override
    public SellerDTO getFollowersAsc(Integer sellerId) {
        Seller response = sellerRepository.getOne(sellerId);
        SellerDTO list = mapSellerEntityToSellerDTO(response);
        Collections.sort(list.getFollowers(), Comparator.comparing(FollowerInfoDTO::getUserName));
        Collections.sort(list.getFollowersSeller(), Comparator.comparing(FollowerSellerInfoDTO::getSellerName));
        return list;
    }

    @Override
    public SellerFollowSellerDTO getFollowedAsc(Integer sellerId) {
        Seller response = sellerRepository.getOne(sellerId);
        SellerFollowSellerDTO list = mapEntityToSellerFollowDTO(response);
        Collections.sort(list.getFollowed(), Comparator.comparing(FollowedSellerInfoDTO::getSellerName));
        return list;
    }

    @Override
    public SellerFollowSellerDTO getFollowedDesc(Integer sellerId) {
        Seller response = sellerRepository.getOne(sellerId);
        SellerFollowSellerDTO list = mapEntityToSellerFollowDTO(response);
        Collections.sort(list.getFollowed(), Comparator.comparing(FollowedSellerInfoDTO::getSellerName).reversed());
        return list;
    }

    @Override
    public SellerDTO getFollowersDesc(Integer sellerId) {
        Seller response = sellerRepository.getOne(sellerId);
        SellerDTO list = mapSellerEntityToSellerDTO(response);
        Collections.sort(list.getFollowers(), Comparator.comparing(FollowerInfoDTO::getUserName).reversed());
        Collections.sort(list.getFollowersSeller(), Comparator.comparing(FollowerSellerInfoDTO::getSellerName).reversed());
        return list;
    }

    private SellerDTO mapSellerEntityToSellerDTO(Seller seller) {
        SellerDTO responseDTO = new SellerDTO();
        responseDTO.setSellerName(seller.getSellerName());
        responseDTO.setSellerId(seller.getSellerId());
        responseDTO.setFollowers(mapFollowers(seller.getFollowers()));
        responseDTO.setFollowersSeller(mapFollowersSeller(seller.getFollowersSellers()));
        return responseDTO;
    }

    private SellerFollowSellerDTO mapEntityToSellerFollowDTO(Seller seller) {
        SellerFollowSellerDTO responseDTO = new SellerFollowSellerDTO();
        responseDTO.setSellerName(seller.getSellerName());
        responseDTO.setSellerId(seller.getSellerId());
        responseDTO.setFollowed(mapFollowedSellers(seller.getFollowedSellers()));
        return responseDTO;
    }

    private SellerProductListDTO mapEntityProductToProductListDTO(Seller seller) {
        SellerProductListDTO responseProductListDTO = new SellerProductListDTO();
        responseProductListDTO.setProducts(mapProductToProductDTO(seller.getProducts()));
        return responseProductListDTO;
    }

    private SellerPromoDTO mapEntityProductToSellerPromoDTO(Seller seller) {
        SellerPromoDTO responsePromoDTO = new SellerPromoDTO();
        responsePromoDTO.setSellerId(seller.getSellerId());
        responsePromoDTO.setSellerName(seller.getSellerName());
        responsePromoDTO.setPosts(mapProductToProductPromoDTO(seller.getProducts()));
        return responsePromoDTO;
    }
    private List<ProductDTO> mapProductToProductDTO(List<Product> product) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        Set<ProductDetail> list = new HashSet<>();
        ProductDetail detail = new ProductDetail();
        product.forEach(product1 -> {
            detail.setProductId(product1.getProductId());
            detail.setProductName(product1.getProductName());
            detail.setProductBrand(product1.getProductBrand());
            detail.setProductType(product1.getProductType());
            detail.setProductColor(product1.getProductColor());
            detail.setProductNotes(product1.getProductNotes());
            ProductDTO productDTO = new ProductDTO();
            productDTO.setDate(product1.getDate());
            productDTO.setId_post(product1.getId_post());
            productDTO.setCategory(product1.getCategory());
            productDTO.setPrice(product1.getPrice());
            productDTO.setSellerId(product1.getSeller().getSellerId());
            list.add(detail);
            productDTO.setDetail(list);
            productDTOList.add(productDTO);
        });
        return productDTOList;
    }

    private List<ProductPromoDTO> mapProductToProductPromoDTO(List<Product> product) {
        List<ProductPromoDTO> productPromoDTOList = new ArrayList<>();
        Set<ProductDetail> list = new HashSet<>();
        ProductDetail detail = new ProductDetail();
        product.forEach(product1 -> {
            detail.setProductId(product1.getProductId());
            detail.setProductName(product1.getProductName());
            detail.setProductBrand(product1.getProductBrand());
            detail.setProductType(product1.getProductType());
            detail.setProductColor(product1.getProductColor());
            detail.setProductNotes(product1.getProductNotes());
            ProductPromoDTO productPromoDTO = new ProductPromoDTO();
            productPromoDTO.setDate(product1.getDate());
            productPromoDTO.setId_post(product1.getId_post());
            productPromoDTO.setCategory(product1.getCategory());
            productPromoDTO.setPrice(product1.getPrice());
            productPromoDTO.setSellerId(product1.getSeller().getSellerId());
            productPromoDTO.setHasPromo(product1.getHasPromo());
            productPromoDTO.setDiscount(product1.getDiscount());
            list.add(detail);
            productPromoDTO.setDetail(list);
            productPromoDTOList.add(productPromoDTO);
        });
        return productPromoDTOList;
    }


    private CountFollowsDTO mapToDTO(Seller seller) {
        CountFollowsDTO responseDTO = new CountFollowsDTO();
        responseDTO.setSellerName(seller.getSellerName());
        responseDTO.setSellerId(seller.getSellerId());
        responseDTO.setFollowers_count(seller.getCountFollowers());
        return responseDTO;
    }

    private void mapDTOToEntity(SellerDTO sellerDTO, Seller seller1) {
        seller1.setSellerName(sellerDTO.getSellerName());
        if (null == seller1.getFollowedSellers()) {
            seller1.setFollowedSellers(new HashSet<>());
        }
        sellerDTO.getFollowers().stream().forEach(follower -> {
            Seller seller = sellerRepository.findBySellerName(follower.getUserName());
            if (null == seller) {
                seller = new Seller();
                seller.setSellerName(new String());
            }
            seller.setSellerName(follower.getUserName());
            seller.followSeller(seller);
        });
    }

    private CountPromoDTO mapCountProductsToDTO(Seller seller) {
        CountPromoDTO responseDTO = new CountPromoDTO();
        responseDTO.setSellerName(seller.getSellerName());
        responseDTO.setId(seller.getSellerId());
        responseDTO.setCountPromos(seller.getCountPromos());
        return responseDTO;
    }

    private List<FollowerInfoDTO> mapFollowers(Set<User> followers){
        List<FollowerInfoDTO> list = new ArrayList<>();
        followers.forEach(follower -> {
            FollowerInfoDTO followersList = new FollowerInfoDTO();
            followersList.setUserName(follower.getUserName());
            followersList.setUserId(follower.getUserID());
            list.add(followersList);
        });
        return list;
    }

    private List<FollowerSellerInfoDTO> mapFollowersSeller(Set<Seller> followers){
        List<FollowerSellerInfoDTO> list = new ArrayList<>();
        followers.forEach(follower -> {
            FollowerSellerInfoDTO followersList = new FollowerSellerInfoDTO();
            followersList.setSellerName(follower.getSellerName());
            followersList.setSellerId(follower.getSellerId());
            list.add(followersList);
        });
        return list;
    }

    private List<FollowedSellerInfoDTO> mapFollowedSellers(Set<Seller> followeds){
        List<FollowedSellerInfoDTO> list = new ArrayList<>();
        followeds.forEach(followed -> {
            FollowedSellerInfoDTO followedsList = new FollowedSellerInfoDTO();
            followedsList.setSellerName(followed.getSellerName());
            followedsList.setSellerId(followed.getSellerId());
            list.add(followedsList);
        });
        return list;
    }
}
