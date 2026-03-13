package com.example.supermarket.common.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * * 会员实体类
 * * @author chen.yang
 * * @date 2026-03-13 09:32:20
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

@Schema(description = "会员信息")
public class MemberDto {
    @Schema(description = "会员id")
    private Long id;
    @Schema(description = "会员用户名")
    private String userName;
    @Schema(description = "会员手机号")
    private String phone;
    @Schema(description = "会员密码")
    private String passWord;
    @Schema(description = "会员姓名")
    private String name;
    @Schema(description = "会员性别")
    private String gender;
    @Schema(description = "会员等级")
    private String level;
    @Schema(description = "会员年龄")
    private Integer age;
    @Schema(description = "会员卡号")
    private String card;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}