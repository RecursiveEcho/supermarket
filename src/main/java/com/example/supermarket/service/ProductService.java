package com.example.supermarket.service;


import com.example.supermarket.common.DTO.ProductDto;
import com.example.supermarket.common.VO.ProductVo;
import com.example.supermarket.common.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有商品
     * @return
     */
    List<ProductVo> findAllProduct();

    /**
     * 删除商品
     * @param id
     */
    void deleteProduct(Long id);

    /**
     * 添加商品
     * @param product
     */
    void addProduct(ProductDto product);

    /**
     * 修改商品
     */
    void UpdateProduct(ProductDto product);

    /**
     * 根据 id 查询商品
     * @param id
     * @return
     */
    ProductVo findProductById(Long id);
}
