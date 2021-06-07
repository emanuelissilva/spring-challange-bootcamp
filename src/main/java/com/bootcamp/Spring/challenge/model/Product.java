package com.bootcamp.Spring.challenge.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seller_sequence")
    @SequenceGenerator(name = "seller_sequence", sequenceName = "seller_sequence")
    private Integer postId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate postDate;

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
}
