package com.example.supermarket.service.impl;

import com.example.supermarket.common.entity.ProductEntity;
import com.example.supermarket.mapper.ProductMapper;
import com.example.supermarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductEntity> findAllProduct() {
        return productMapper.findAllProduct();
    }

    @Override
    public void deleteProduct(Long id) {
        productMapper.deleteProduct(id);
    }

    @Override
    public void addProduct(ProductEntity productEntity) {
        productEntity.setCreateTime(LocalDateTime.now());
        productEntity.setUpdateTime(LocalDateTime.now());
        productMapper.addProduct(productEntity);
    }

    @Override
    public void UpdateProduct(ProductEntity productEntity) {
        productEntity.setUpdateTime(LocalDateTime.now());
        productMapper.UpdateProduct(productEntity);
    }

    @Override
    public ProductEntity findProductById(Long id) {
        return productMapper.findProductById(id);
    }
}
