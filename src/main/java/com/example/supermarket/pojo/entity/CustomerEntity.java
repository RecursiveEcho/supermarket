package com.example.supermarket.pojo.entity;

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
 * @date 2026-03-15 10:15:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "客户")
public class CustomerEntity {
    @Schema(description = "客户 id")
    private Long id;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "客户姓名")
    private String name;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "性别")
    private String gender;

    @Schema(description = "联系方式")
    private String phone;

    @Schema(description = "收货地址")
    private String address;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "修改时间")
    private LocalDateTime updateTime;
}