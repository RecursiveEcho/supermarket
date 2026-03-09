package com.example.supermarket.common.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录DTO
 * <p>
 * 用户登录时的入参信息
 * </p>
 *
 * @author chen.yang
 * @date 2026-03-09 11:40:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "用户登录 DTO", description = "用户登录数据传输对象")
public class UserDto {
    @Schema(title = "用户名")
    private String userName;
    @Schema(title = "密码")
    private String passWord;
    @Schema(title = "token")
    private String token;
}