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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence")
    private Integer postId;

    @Column(name="date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate postDate;

    @Column(name="category")
    private Integer category;

    @Column(name="price")
    private Double price;

    @Column(name="productId")
    private Integer productId;

    @Column(name="productName")
    private String productName;

    @Column(name="productType")
    private String productType;

    @Column(name="productBrand")
    private String productBrand;

    @Column(name="productColor")
    private String productColor;

    @Column(name="productNotes")
    private String productNotes;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="seller_id")
    private Seller seller;


}
