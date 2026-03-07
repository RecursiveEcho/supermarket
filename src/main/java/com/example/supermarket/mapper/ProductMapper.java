package com.example.supermarket.mapper;

import com.example.supermarket.entity.Product_entity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("select id, name, cost_price, selling_price, stock,create_time,update_time from product order by create_time desc;")
     List<Product_entity> findAllProduct();

    @Delete("delete from product where id=#{id};")
    void deleteProduct(Long id);

    @Insert("insert into product(name,cost_price,selling_price,stock,create_time,update_time) values " +
            "(#{name},#{costPrice},#{sellingPrice},#{stock}," +
            "#{createTime},#{updateTime})")//插入数据后面的参数是实体类的命名方式
    void addProduct(Product_entity productEntity);
}
