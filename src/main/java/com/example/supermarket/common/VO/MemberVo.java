package com.example.supermarket.common.VO;

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
 * @date 2026-03-13 09:42:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVo {
    @Schema(description = "会员id")
    private Long id;
    @Schema(description = "会员用户名")
    private String userName;
    @Schema(description = "会员姓名")
    private String name;
    @Schema(description = "会员年龄")
    private Integer age;
    @Schema(description = "会员手机号")
    private String phone;
    @Schema(description = "会员性别")
    private String gender;
    @Schema(description = "会员卡号")
    private String card;
    @Schema(description = "会员等级")
    private String level;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "修改时间")
    private LocalDateTime updateTime;
}