package com.example.supermarket.service.impl;

import com.example.supermarket.entity.Product_entity;
import com.example.supermarket.mapper.ProductMapper;
import com.example.supermarket.service.ProductService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product_entity> findAllProduct() {
        return productMapper.findAllProduct();
    }

    @Override
    public void deleteProduct(Long id) {
        productMapper.deleteProduct(id);
    }

    @Override
    public void addProduct(Product_entity productEntity) {
        productEntity.setCreateTime(LocalDateTime.now());
        productEntity.setUpdateTime(LocalDateTime.now());
        productMapper.addProduct(productEntity);
    }
}
