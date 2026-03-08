package com.example.supermarket.entity;

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
    private Long id;
    private String username;
    private String token;
}
