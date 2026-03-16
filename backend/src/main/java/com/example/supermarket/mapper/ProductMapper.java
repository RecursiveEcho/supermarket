package com.example.supermarket.mapper;

import com.example.supermarket.pojo.DTO.ProductQueryDto;
import com.example.supermarket.pojo.VO.ProductVo;
import com.example.supermarket.pojo.entity.ProductEntity;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品类mapper接口
 * @author chen.yang
 * @date 2026-03-13 09:47:56
 */
@Mapper
public interface ProductMapper {

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
     * @param productEntity
     */

    void addProduct(ProductEntity productEntity);


    /**
     * 修改商品信息
     * @param productEntity
     */
    void updateProduct(ProductEntity productEntity);

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

    /**
     * 分页查询商品
     * @param name
     * @param costPrice
     * @param sellingPrice
     * @param createTime
     * @param updateTime
     * @return
     */
    List<ProductEntity> list(String name, Double costPrice, Double sellingPrice, LocalDateTime createTime, LocalDateTime updateTime);
}
