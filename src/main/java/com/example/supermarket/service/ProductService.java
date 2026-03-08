package com.example.supermarket.service;


import com.example.supermarket.entity.Product_entity;
import com.example.supermarket.entity.Result;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有商品
     * @return
     */
    List<Product_entity> findAllProduct();

    /**
     * 删除商品
     * @param id
     */
    void deleteProduct(Long id);

    /**
     * 添加商品
     * @param productEntity
     */
    void addProduct(Product_entity productEntity);

    /**
     * 修改商品
     */
    void UpdateProduct(Product_entity productEntity);

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    Product_entity findProductById(Long id);
}
