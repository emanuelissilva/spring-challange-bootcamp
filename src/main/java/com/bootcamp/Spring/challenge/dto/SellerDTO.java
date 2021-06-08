package com.bootcamp.Spring.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class SellerDTO {
    private Integer sellerId;
    private String sellerName;
    private List<FollowerInfoDTO> followers = new ArrayList<>();
    private List<FollowerSellerInfoDTO> followersSeller = new ArrayList<>();
}
