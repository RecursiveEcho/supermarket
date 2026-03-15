package com.example.supermarket.pojo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @Schema(title = "id")
    private Long id;
    @Schema(title = "名称")
    private String name;
    @Schema(title = "成本价")
    private Double costPrice;
    @Schema(title = "售价")
    private Double sellingPrice;
    @Schema(title = "库存")
    private Integer stock;
    @Schema(title = "修改时间")
    private LocalDateTime updateTime;
}
