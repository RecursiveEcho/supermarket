package com.example.supermarket.pojo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页查询参数DTO
 * <p>
 * 分页查询时的入参信息
 * </p>
 *
 * @author chen.yang
 * @date 2026-03-10 19:42:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {
    @Schema(description = "当前页码")
    private Integer pageNum;
    @Schema(description = "每页数量")
    private Integer pageSize;
}