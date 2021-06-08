package com.bootcamp.Spring.challenge.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowedInfoDTO{
    private Integer sellerId;
    private String sellerName;

    public FollowedInfoDTO() {
    }
}
