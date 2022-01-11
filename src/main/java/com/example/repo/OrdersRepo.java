package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Orders;

public interface OrdersRepo extends JpaRepository<Orders, Integer> {

}
