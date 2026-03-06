package com.example.supermarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product_entity {
    private Long id;//商品id
    private String name;//商品名称
    private Double costPrice;//成本价
    private Double sellingPrice;//售价
    private Integer stock;//库存数量
}
