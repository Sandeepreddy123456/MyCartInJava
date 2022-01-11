package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.MyCart;

public interface MyCartRepo extends  JpaRepository<MyCart, Integer>{

}
