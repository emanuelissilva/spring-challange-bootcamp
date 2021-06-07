package com.bootcamp.Spring.challenge.service.impl;

import com.bootcamp.Spring.challenge.dto.*;
import com.bootcamp.Spring.challenge.model.Product;
import com.bootcamp.Spring.challenge.model.ProductDetail;
import com.bootcamp.Spring.challenge.model.Seller;
import com.bootcamp.Spring.challenge.model.User;
import com.bootcamp.Spring.challenge.repositories.SellerRepository;
import com.bootcamp.Spring.challenge.repositories.UserRepository;
import com.bootcamp.Spring.challenge.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;
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

    @Override
    public UserFollowedListDTO getFollowedSellerAsc(Integer userId) {
        User response = userRepository.getOne(userId);
        UserFollowedListDTO list = mapUserFollowedListToDTO(response);
        Collections.sort(list.getFollowed(), Comparator.comparing(FollowedInfoDTO::getSellerName));
        return list;
    }

    @Override
    public UserFollowedListDTO getFollowedSellerDesc(Integer userId) {
        User response = userRepository.getOne(userId);
        UserFollowedListDTO list = mapUserFollowedListToDTO(response);
        Collections.sort(list.getFollowed(), Comparator.comparing(FollowedInfoDTO::getSellerName).reversed());
        return list;
    }

    @Transactional
    @Override
    public UserDTO unfollowSeller(Integer userId, Integer sellerId) {
        User user = userRepository.getOne(userId);
        Seller seller = sellerRepository.getOne(sellerId);
        user.getFollowedSellers().remove(seller);
        User userSaved = userRepository.save(user);
        return mapEntityToDTO(user);
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

    @Override
    public UserFollowedProductListDTO getProductList(Integer idSeller) {
        UserFollowedProductListDTO user = new UserFollowedProductListDTO();
        UserFollowedProductListDTO user2 = new UserFollowedProductListDTO();
        List<ProductDTO> posts = new ArrayList<>();
        User user1 = userRepository.getOne(idSeller);
        user.setUserName(user1.getUserName());
        user.setUserId(user1.getUserID());
        user1.getFollowedSellers().forEach(seller -> {
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
    public UserFollowedProductListDTO getProductDesc(Integer sellerId) {
        UserFollowedProductListDTO user = new UserFollowedProductListDTO();
        User user1 = userRepository.getOne(sellerId);
        user.setUserName(user1.getUserName());
        user.setUserId(user1.getUserID());
        user1.getFollowedSellers().forEach(seller -> {
            user.setPosts(mapProductToProductDTO(seller.getProducts()));
        });
        Collections.sort(user.getPosts(), Comparator.comparing(ProductDTO::getDate).reversed());
        return user;
    }

    @Override
    public UserFollowedProductListDTO getProductAsc(Integer sellerId) {
        UserFollowedProductListDTO user = new UserFollowedProductListDTO();
        User user1 = userRepository.getOne(sellerId);
        user.setUserName(user1.getUserName());
        user.setUserId(user1.getUserID());
        user1.getFollowedSellers().forEach(seller -> {
            user.setPosts(mapProductToProductDTO(seller.getProducts()));
        });
        Collections.sort(user.getPosts(), Comparator.comparing(ProductDTO::getDate));
        return user;
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
                .sorted()
                .collect(Collectors.toList()));
        return responseDTO;
    }

    private UserFollowedListDTO mapUserFollowedListToDTO(User user) {
        UserFollowedListDTO responseDTO = new UserFollowedListDTO();
        responseDTO.setUserName(user.getUserName());
        responseDTO.setUserId(user.getUserID());
        responseDTO.setFollowed(mapFollowed(user.getFollowedSellers()));
        return responseDTO;
    }

    private List<FollowedInfoDTO> mapFollowed(Set<Seller> followedList){
        List<FollowedInfoDTO> list = new ArrayList<>();
        followedList.forEach(followed -> {
            FollowedInfoDTO followedListFinal = new FollowedInfoDTO();
            followedListFinal.setSellerName(followed.getSellerName());
            followedListFinal.setSellerId(followed.getSellerId());
            list.add(followedListFinal);
        });
        return list;
    }
}
