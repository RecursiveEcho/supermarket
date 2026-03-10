package com.example.supermarket.mapper;

import com.example.supermarket.common.DTO.ProductDto;
import com.example.supermarket.common.DTO.ProductQueryDto;
import com.example.supermarket.common.VO.ProductVo;
import com.example.supermarket.common.entity.ProductEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

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
     * 修改商品信息
     * @param productEntity
     */
    void UpdateProduct(ProductEntity productEntity);

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    ProductEntity findProductById(Long id);

    /**
     * 动态查询商品
     * @param productQueryDto
     * @return
     */
    List<ProductEntity> queryProduct(ProductQueryDto productQueryDto);

    /**
     * 动态修改商品信息
     * @param productEntityList
     */
    void updateProducts(List<ProductEntity> productEntityList);

    /**
     * 批量删除商品
     * @param idList
     */
    void deleteProducts(List<Long> idList);
}
