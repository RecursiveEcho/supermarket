package com.example.supermarket.common.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 实现功能【】
 * <p>
 *
 * </p>
 *
 * @author chen.yang
 * @date 2026-03-13 18:53:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "员工实体类")
public class ClerkEntity {
    @Schema(description = "员工 id")
    private Long id;
    @Schema(description = "员工姓名")
    private String name;
    @Schema(description = "员工用户名")
    private String userName;
    @Schema(description = "员工头像")
    private String avatar;
    @Schema(description = "员工性别")
    private String gender;
    @Schema(description = "员工联系方式")
    private String phone;
    @Schema(description = "员工岗位")
    private String position;
    @Schema(description = "员工薪资")
    private Double salary;
    @Schema(description = "入职日期")
    private LocalDateTime entryDate;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}