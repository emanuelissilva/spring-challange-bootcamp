package com.bootcamp.Spring.challenge.service.impl;

import com.bootcamp.Spring.challenge.dto.ProductDTO;
import com.bootcamp.Spring.challenge.model.Product;
import com.bootcamp.Spring.challenge.model.ProductDetail;
import com.bootcamp.Spring.challenge.model.Seller;
import com.bootcamp.Spring.challenge.repositories.ProductRepository;
import com.bootcamp.Spring.challenge.repositories.SellerRepository;
import com.bootcamp.Spring.challenge.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductRepository productRepository;

    @Resource
    private SellerRepository sellerRepository;

    @Override
    @Transactional
    public ProductDTO postProduct(ProductDTO productDTO) {
        Product product = new Product();
        mapDTOToEntity(productDTO, product);
        Product savedProduct = productRepository.save(product);
        return mapProductToProductDTO(savedProduct);
    }

    private void mapDTOToEntity(ProductDTO productDTO, Product product) {
        product.setPostDate(productDTO.getPostDate());
        product.setPostId(productDTO.getPostId());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        product.setProductName(mapDTOdetailListtoProductDetail(productDTO).getProductName());
        product.setProductId(mapDTOdetailListtoProductDetail(productDTO).getProductId());
        if (null == product.getSeller()) {
            product.setSeller(new Seller());
        }
        Seller seller = sellerRepository.findBySellerId(productDTO.getSellerId());
        product.setSeller(seller);
        product.setProductBrand(mapDTOdetailListtoProductDetail(productDTO).getProductBrand());
        product.setProductType(mapDTOdetailListtoProductDetail(productDTO).getProductType());
        product.setProductColor(mapDTOdetailListtoProductDetail(productDTO).getProductColor());
        product.setProductColor(mapDTOdetailListtoProductDetail(productDTO).getProductColor());
        product.setProductNotes(mapDTOdetailListtoProductDetail(productDTO).getProductNotes());

    }

    private ProductDTO mapProductToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPostDate(product.getPostDate());
        productDTO.setPostId(product.getPostId());
        productDTO.setCategory(product.getCategory());
        productDTO.setPrice(product.getPrice());
        productDTO.setSellerId(product.getSeller().getSellerId());
        Set<ProductDetail> list = new HashSet<>();
        ProductDetail detail = new ProductDetail();
        detail.setProductId(product.getProductId());
        detail.setProductName(product.getProductName());
        detail.setProductBrand(product.getProductBrand());
        detail.setProductType(product.getProductType());
        detail.setProductColor(product.getProductColor());
        detail.setProductNotes(product.getProductNotes());
        list.add(detail);
        productDTO.setDetail(list);
        return productDTO;
    }

    private ProductDetail mapDTOdetailListtoProductDetail(ProductDTO productDTO){
        ProductDetail details = new ProductDetail();
        productDTO.getDetail().forEach(detail -> {
            details.setProductId(detail.getProductId());
            details.setProductBrand(detail.getProductBrand());
            details.setProductName(detail.getProductName());
            details.setProductType(detail.getProductType());
            details.setProductColor(detail.getProductColor());
            details.setProductNotes(detail.getProductNotes());
        });
        return details;
    }

}
