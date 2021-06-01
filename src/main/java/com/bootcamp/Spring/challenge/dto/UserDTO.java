package com.bootcamp.Spring.challenge.dto;

import com.bootcamp.Spring.challenge.model.Seller;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    private long id;
    private String userName;
    private List<String> followedSellers = new ArrayList<>();
}
