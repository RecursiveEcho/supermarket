package com.example.supermarket.service;


import com.example.supermarket.pojo.DTO.ProductDto;
import com.example.supermarket.pojo.DTO.ProductQueryDto;
import com.example.supermarket.pojo.VO.ProductVo;
import com.example.supermarket.pojo.entity.PageResult;

import java.util.List;

public interface ProductService {

    PageResult<ProductVo> findProductByPage(Integer pageNum, Integer pageSize);
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
    void updateProduct(ProductDto product);

    /**
     * 根据 id 查询商品
     * @param id
     * @return
     */
    ProductVo findProductById(Long id);

    /**
     * 查询商品
     * @param productQueryDto
     * @return
     */
    List<ProductVo> queryProduct(ProductQueryDto productQueryDto);

    /**
     * 批量修改商品
     * @param productList
     */
    void updateProducts(List<ProductDto> productList);

    /**
     * 批量删除商品
     * @param idList
     */
    void deleteProducts(List<Long> idList);
}
