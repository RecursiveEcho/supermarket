package com.example.supermarket.pojo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * 商品查询DTO
 * @author chen.yang
 * @date 2026-03-10 11:23:21
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductQueryDto {
    @Schema(title = "id")
    private Long id;
    @Schema(title = "商品名字")
    private String name;
    @Schema(title =" 商品成本价格")
    private Double costPrice;
    @Schema(title = "商品销售价格")
    private Double sellingPrice;
    @Schema(title="商品上架时间")
    private LocalDateTime createTime;
    @Schema(title = "商品更新时间")
    private LocalDateTime updateTime;
}