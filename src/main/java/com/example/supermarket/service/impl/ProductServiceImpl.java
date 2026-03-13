package com.example.supermarket.service.impl;

import com.example.supermarket.common.DTO.PageDto;
import com.example.supermarket.common.DTO.ProductDto;
import com.example.supermarket.common.DTO.ProductQueryDto;
import com.example.supermarket.common.VO.MemberVo;
import com.example.supermarket.common.VO.ProductVo;
import com.example.supermarket.common.entity.PageResult;
import com.example.supermarket.common.entity.ProductEntity;
import com.example.supermarket.mapper.ProductMapper;
import com.example.supermarket.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品服务实现类
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageResult<ProductVo> findProductByPage(Integer pageNum, Integer pageSize) {
            // 打印日志确认参数
            log.info("分页查询 - pageNum: {}, pageSize: {}", pageNum, pageSize);

            // 设置分页参数
            PageHelper.startPage(pageNum, pageSize);
            List<ProductVo> productList = productMapper.findAllProduct();

            // 使用 PageInfo 包装查询结果
            PageInfo<ProductVo> pageInfo = new PageInfo<>(productList);

            // 打印日志确认分页信息
            log.info("分页结果 - pageNum: {}, pageSize: {}, total: {}",
                    pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal());

            // 使用静态工厂方法构建分页结果
            return PageResult.of(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 查询所有商品
     * @return
     */
    @Override
    public List<ProductVo> findAllProduct() {
        List<ProductVo> productList = productMapper.findAllProduct();
        return productList;
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
    public void updateProduct(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDto, productEntity);
        productEntity.setUpdateTime(LocalDateTime.now());
        productMapper.updateProduct(productEntity);
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

    /**
     * 批量修改商品信息
     * @param productList
     */
    @Override
    public void updateProducts(List<ProductDto> productList) {
        List<ProductEntity> productEntityList = productList.stream()
            .map(this::convertToEntity)
            .collect(Collectors.toList());//转换为List
        productMapper.updateProducts(productEntityList);
    }

    /**
     * 批量删除商品
     * @param idList
     */
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

    /**
     * 将DTO转换为实体类
     * @param dto
     * @return
     */
    private ProductEntity convertToEntity(ProductDto dto) {
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
