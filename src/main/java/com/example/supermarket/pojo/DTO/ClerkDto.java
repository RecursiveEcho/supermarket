package com.example.supermarket.pojo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 会员类
 * @author chen.yang
 * @date 2026-03-13 18:55:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "员工DTO")
public class ClerkDto {
    @Schema(description = "员工id")
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
}