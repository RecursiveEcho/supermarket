package com.example.supermarket.pojo.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 登陆信息
 */
public class LoginInfo {
    @Schema(title = "id")
    private Long id;
    @Schema(title = "用户名")
    private String username;
    @Schema(title = "token")
    private String token;
}
