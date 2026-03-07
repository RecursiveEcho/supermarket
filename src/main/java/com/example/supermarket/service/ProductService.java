package com.example.supermarket.service;


import com.example.supermarket.entity.Product_entity;
import com.example.supermarket.entity.Result;

import java.util.List;

public interface ProductService {
    List<Product_entity> findAllProduct();

    void deleteProduct(Long id);

    void addProduct(Product_entity productEntity);
}
