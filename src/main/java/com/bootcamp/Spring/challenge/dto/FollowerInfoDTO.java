package com.bootcamp.Spring.challenge.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowerInfoDTO {
    private long userId;
    private String userName;

    public FollowerInfoDTO() {
    }
}
