package com.example.supermarket.common.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页结果 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "分页结果", description = "分页查询返回的结果")
public class PageResult<T> {

    @Schema(description = "当前页码")
    private Integer pageNum;

    @Schema(description = "每页数量")
    private Integer pageSize;

    @Schema(description = "总记录数")
    private Long total;

    @Schema(description = "总页数")
    private Integer pages;

    @Schema(description = "是否有上一页")
    private Boolean hasPrevious;

    @Schema(description = "是否有下一页")
    private Boolean hasNext;

    @Schema(description = "数据列表")
    private List<T> list;
    
    /**
     * 构造函数（根据 Page 对象自动计算）
     */
    public PageResult(Long total, List<T> list) {
        this.total = total;
        this.list = list;
        this.pageNum = 1;
        this.pageSize = 10;
        this.pages = (int) Math.ceil((double) total / 10);
        this.hasPrevious = false;
        this.hasNext = false;
    }

    /**
     * 构建分页结果
     */
    public static <T> PageResult<T> of(Integer pageNum, Integer pageSize, Long total, List<T> list) {
        PageResult<T> result = new PageResult<>();
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        result.setTotal(total);
        result.setList(list);

        // 计算总页数
        int pages = (int) Math.ceil((double) total / pageSize);
        result.setPages(pages);

        // 计算是否有上一页和下一页
        result.setHasPrevious(pageNum > 1);
        result.setHasNext(pageNum < pages);

        return result;
    }
}
