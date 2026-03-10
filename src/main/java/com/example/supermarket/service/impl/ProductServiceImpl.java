package com.example.supermarket.service.impl;

import com.example.supermarket.common.DTO.ProductDto;
import com.example.supermarket.common.DTO.ProductQueryDto;
import com.example.supermarket.common.VO.ProductVo;
import com.example.supermarket.common.entity.ProductEntity;
import com.example.supermarket.mapper.ProductMapper;
import com.example.supermarket.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品服务实现类
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询所有商品
     * @return
     */
    @Override
    public List<ProductVo> findAllProduct() {
        List<ProductEntity> productList = productMapper.findAllProduct();
        return productList.stream()
            .map(this::convertToVo)
            .collect(Collectors.toList());
    }

    /**
     * 删除商品
     * @param id
     */
    @Override
    public void deleteProduct(Long id) {
        productMapper.deleteProduct(id);
    }


    /**
     * 添加商品
     * @param productDto
     */
    @Override
    public void addProduct(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDto, productEntity);
        productEntity.setCreateTime(LocalDateTime.now());
        productEntity.setUpdateTime(LocalDateTime.now());
        productMapper.addProduct(productEntity);
    }


    /**
     * 修改商品信息
     * @param productDto
     */
    @Override
    public void UpdateProduct(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDto, productEntity);
        productEntity.setUpdateTime(LocalDateTime.now());
        productMapper.UpdateProduct(productEntity);
    }


    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    @Override
    public ProductVo findProductById(Long id) {
        ProductEntity product = productMapper.findProductById(id);
        if (product != null) {
            return convertToVo(product);
        }
        return null;
    }

    /**
     * 查询商品
     * @param productQueryDto
     * @return
     */
    @Override
    public List<ProductVo> queryProduct(ProductQueryDto productQueryDto) {
        List<ProductEntity> productList = productMapper.queryProduct(productQueryDto);//先把前端的数据处理
        return productList.stream()//通过stream流处理数据
            .map(this::convertToVo)//转换为VO
            .collect(Collectors.toList());//转换为List
    }

    @Override
    public void updateProducts(List<ProductDto> productList) {
        List<ProductEntity> productEntityList = productList.stream()
            .map(this::convertToEntity)
            .collect(Collectors.toList());//转换为List
        productMapper.updateProducts(productEntityList);
    }

    @Override
    public void deleteProducts(List<Long> idList) {
        productMapper.deleteProducts(idList);
    }


    /**
     * 将实体类转换为VO
     * @param entity
     * @return
     */
    //转换方法，将实体类转换为VO
    private ProductVo convertToVo(ProductEntity entity) {
        ProductVo vo = new ProductVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    private ProductEntity convertToEntity(ProductDto dto) {
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
