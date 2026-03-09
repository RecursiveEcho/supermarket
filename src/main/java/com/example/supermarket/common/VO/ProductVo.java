package com.example.supermarket.common.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 商品信息VO
 * <p>
 * 商品数据传输对象
 * </p>
 *
 * @author chen.yang
 * @date 2026-03-09 11:49:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "商品信息")
public class ProductVo {
    private Long id;
    @Schema(description = "商品名称")
    private String name;
    @Schema(description = "商品价格")
    private Double costPrice;
    @Schema(description = "商品售价")
    private String sellingPrice;
    @Schema(description = "商品库存数量")
    private Integer stock;
    @Schema(description = "商品上架时间")
    private LocalDateTime createTime;
    @Schema(description = "商品更新时间")
    private LocalDateTime updateTime;
}