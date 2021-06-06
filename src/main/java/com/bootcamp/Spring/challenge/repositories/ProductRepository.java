package com.bootcamp.Spring.challenge.repositories;

import com.bootcamp.Spring.challenge.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByOrderByPostDateDesc();

}
