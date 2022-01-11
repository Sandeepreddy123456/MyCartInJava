package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Products;

public interface ProductRepo extends JpaRepository<Products, Integer> {

}
