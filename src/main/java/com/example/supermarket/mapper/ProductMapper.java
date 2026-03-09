package com.example.supermarket.mapper;

import com.example.supermarket.common.entity.ProductEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {


    @Select("select id, name, cost_price, selling_price, stock,create_time,update_time from product order by create_time desc;")
     List<ProductEntity> findAllProduct();


    @Delete("delete from product where id=#{id};")
    void deleteProduct(Long id);


    @Insert("insert into product(name,cost_price,selling_price,stock,create_time,update_time) values " +
            "(#{name},#{costPrice},#{sellingPrice},#{stock}," +
            "#{createTime},#{updateTime})")//插入数据后面的参数是实体类的命名方式
    void addProduct(ProductEntity productEntity);


    @Update("update product set name=#{name},cost_price=#{costPrice},selling_price=#{sellingPrice},stock=#{stock},update_time=#{updateTime} where id=#{id};")
    void UpdateProduct(ProductEntity productEntity);

    @Select("select id, name, cost_price, selling_price, stock,create_time,update_time from product where id=#{id};")
    ProductEntity findProductById(Long id);
}
