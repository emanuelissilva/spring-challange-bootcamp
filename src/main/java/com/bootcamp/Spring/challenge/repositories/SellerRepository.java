package com.bootcamp.Spring.challenge.repositories;

import com.bootcamp.Spring.challenge.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    public Seller findBySellerName(String sellerName);
}
