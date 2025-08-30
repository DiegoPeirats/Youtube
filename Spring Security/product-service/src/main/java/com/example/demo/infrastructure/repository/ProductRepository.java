package com.example.demo.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
