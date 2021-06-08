package com.bootcamp.Spring.challenge.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
    @SequenceGenerator(name = "post_sequence", sequenceName = "post_sequence")
    private Integer id_post;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    private Integer category;

    private Double price;

    private Integer productId;

    private String productName;

    private String productType;

    private String productBrand;

    private String productColor;

    private String productNotes;

    private Boolean hasPromo;

    private Double discount;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="seller_id")
    private Seller seller;

    public Double setDiscount(Double discount) {
        if(this.hasPromo){
            this.discount=discount;
        } else if(this.hasPromo==null){
            this.discount=null;
        } else
        this.discount=0.0;

        return this.discount;
    }

    public Product() {
    }

    public Product(Integer id_post, LocalDate date, Integer category, Double price, Integer productId, String productName, String productType, String productBrand, String productColor, String productNotes, Boolean hasPromo, Double discount, Seller seller) {
        this.id_post = id_post;
        this.date = date;
        this.category = category;
        this.price = price;
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.productBrand = productBrand;
        this.productColor = productColor;
        this.productNotes = productNotes;
        this.hasPromo = hasPromo;
        this.discount = discount;
        this.seller = seller;
    }
}
