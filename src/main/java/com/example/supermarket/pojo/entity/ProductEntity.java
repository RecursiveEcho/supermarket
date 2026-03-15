package com.example.supermarket.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 商品新增DTO
 * <p>
 * 商品新增时的入参信息
 * </p>
 *
 * @author chen.yang
 * @date 2026-03-09 11:40:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

@Schema(title = "商品新增DTO", description = "新增商品时的入参信息")
public class ProductEntity {
    @Schema(title = "id", description = "商品id")
    private Long id;
    @Schema(title = "名称", description = "商品名称")
    private String name;
    @Schema(title = "成本价", description = "商品成本价")
    private Double costPrice;
    @Schema(title = "售价", description = "商品售价")
    private Double sellingPrice;
    @Schema(title="库存",description="商品库存数量")
    private Integer stock;
    @Schema(title = "上架时间", description = "商品上架时间")
    private LocalDateTime createTime;
    @Schema(title = "更新时间", description = "商品更新时间")
    private LocalDateTime updateTime;
}
