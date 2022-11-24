package com.example.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.productservice.models.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    
}
