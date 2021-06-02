package com.bootcamp.Spring.challenge.repositories;

import com.bootcamp.Spring.challenge.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
