package com.example.springdbtestdemo.repository;

import com.example.springdbtestdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
