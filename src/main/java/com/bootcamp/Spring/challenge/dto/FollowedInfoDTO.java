package com.bootcamp.Spring.challenge.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowedInfoDTO{
    private long sellerId;
    private String sellerName;

    public FollowedInfoDTO() {
    }
}
