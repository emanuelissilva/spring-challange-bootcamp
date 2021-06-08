package com.bootcamp.Spring.challenge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence")
    private Integer userID;

    private String userName;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
        name="user_follows_sellers",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name="seller_id")
    )

    private Set<Seller> followedSellers = new HashSet<>();

    public void followSeller(Seller seller) {
        this.followedSellers.add(seller);
        seller.getFollowers().add(this);
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User() {
    }

}
