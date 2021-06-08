package com.bootcamp.Spring.challenge.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountFollowsDTO {
    private Integer sellerId;
    private String sellerName;
    private Integer followers_count;
}
