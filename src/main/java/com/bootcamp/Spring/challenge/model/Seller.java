package com.bootcamp.Spring.challenge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seller_sequence")
    @SequenceGenerator(name = "seller_sequence", sequenceName = "seller_sequence")
    private Integer sellerId;

    //@Column(name="sellerName")
    private String sellerName;

    //@Column(name="countFollowers")
    private Integer countFollowers;

    @ManyToMany(mappedBy="followedSellers")
    private Set<User> followers = new HashSet<>();

    public Seller(String sellerName) {
        this.sellerName = sellerName;
    }

    public Seller() {
    }

    @OneToMany(mappedBy = "seller")
    @Column(name = "products")
    private List<Product> products;

}