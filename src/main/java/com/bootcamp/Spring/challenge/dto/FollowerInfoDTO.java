package com.bootcamp.Spring.challenge.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowerInfoDTO {
    private Integer userId;
    private String userName;

    public FollowerInfoDTO() {
    }
}
