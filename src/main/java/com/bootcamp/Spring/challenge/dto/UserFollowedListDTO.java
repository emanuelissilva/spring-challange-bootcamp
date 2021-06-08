package com.bootcamp.Spring.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserFollowedListDTO {
    private Integer userId;
    private String userName;
    private List<FollowedInfoDTO> followed = new ArrayList<>();
}
