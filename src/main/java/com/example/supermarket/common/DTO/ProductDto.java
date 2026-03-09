package com.example.supermarket.common.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品新增DTO
 * <p>
 * 新增商品时的入参信息
 * </p>
 *
 * @author chen.yang
 * @date 2026-03-09 11:40:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "商品信息 DTO", description = "商品数据传输对象")
public class ProductDto {
    @Schema(title = "名称")
    private String name;
    @Schema(title = "成本价")
    private Double costPrice;
    @Schema(title = "售价")
    private Double sellingPrice;
    @Schema(title = "库存")
    private Integer stock;
}
