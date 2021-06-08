package com.bootcamp.Spring.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserFollowedProductListDTO {
    private Integer userId;
    private String userName;
    private List<ProductDTO> posts = new ArrayList<>();

    public void setPosts(List<ProductDTO> posts) {
        this.posts.addAll(posts);
    }
}
