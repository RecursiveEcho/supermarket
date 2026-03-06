package com.example.supermarket.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User_entity {
    private Long id;//用户id
    private String userName;//用户名
    private String passWord;//密码
    private LocalDateTime createTime;//创建时间
    private LocalDateTime dateTime;//更新时间
}
