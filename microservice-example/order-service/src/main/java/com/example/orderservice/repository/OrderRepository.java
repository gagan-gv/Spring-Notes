package com.example.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.orderservice.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
