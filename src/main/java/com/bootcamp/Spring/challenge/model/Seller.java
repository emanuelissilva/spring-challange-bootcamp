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
    private String sellerName;
    private Integer followers_count;
    private Integer countPromos=0;

    @ManyToMany(mappedBy="followedSellers")
    private Set<User> followers = new HashSet<>();

    @ManyToMany(mappedBy="followedSellers")
    private Set<Seller> followersSellers = new HashSet<>();

    @ManyToMany(mappedBy="followed")
    private Set<Seller> followedSellers = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
            name="seller_followed_sellers",
            joinColumns = @JoinColumn(name = "followed_id"),
            inverseJoinColumns = @JoinColumn(name="follower_id")
    )
    private Set<Seller> followed = new HashSet<>();

    public void followSeller(Seller seller){
        if(this.getSellerId()==seller.getSellerId()){
            System.out.println("A seller can't follow himself");
        } else
            this.followed.add(seller);
        seller.getFollowedSellers().add(this);
    }

    public void unfollowSeller(Seller seller) {
        if(this.getSellerId()==seller.getSellerId()){
            System.out.println("A seller can't unfollow himself");
        } else
            this.followed.remove(seller);
        seller.getFollowedSellers().remove(this);
    }

    public Seller(String sellerName) {
        this.sellerName = sellerName;
    }

    public Seller() {}

    @OneToMany(mappedBy = "seller")
    private List<Product> products;

    public Integer getCountFollowers() {
        return this.followers.size()+this.followersSellers.size();
    }

    public Integer getCountPromos() {
        final Integer[] countPromo = {0};
        this.products.forEach(product -> {
            if(product.getHasPromo()){
                countPromo[0]++;
            }else if (product.getHasPromo()==null)
                countPromo[0]=countPromo[0];
        });
        return this.countPromos=countPromo[0];
    }
}