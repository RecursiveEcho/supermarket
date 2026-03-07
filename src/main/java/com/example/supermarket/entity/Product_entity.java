package com.example.supermarket.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Schema(title = "商品新增DTO", description = "新增商品时的入参信息")
public class Product_entity {
    @Schema(title = "id", description = "商品id")
    private Long id;//商品id
    @Schema(title = "名称", description = "商品名称")
    private String name;//商品名称
    @Schema(title = "成本价", description = "商品成本价")
    private Double costPrice;//成本价
    @Schema(title = "售价", description = "商品售价")
    private Double sellingPrice;//售价
    @Schema(title="库存",description="商品库存数量")
    private Integer stock;//库存数量
    @Schema(title = "上架时间", description = "商品上架时间")
    private LocalDateTime createTime;//上架时间
    @Schema(title = "更新时间", description = "商品更新时间")
    private LocalDateTime updateTime;//更新时间
}
