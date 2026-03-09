package com.example.supermarket.service;


import com.example.supermarket.common.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有商品
     * @return
     */
    List<ProductEntity> findAllProduct();

    /**
     * 删除商品
     * @param id
     */
    void deleteProduct(Long id);

    /**
     * 添加商品
     * @param productEntity
     */
    void addProduct(ProductEntity productEntity);

    /**
     * 修改商品
     */
    void UpdateProduct(ProductEntity productEntity);

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    ProductEntity findProductById(Long id);
}
