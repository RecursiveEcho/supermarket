package com.example.supermarket.entity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title="用户实体类",description="用户实体类")
public class User_entity {
    @Schema(title ="id",description="用户id")
    private Long id;//用户id
    @Schema(title ="用户名",description="用户名")
    private String userName;//用户名
    @Schema(title ="密码",description="密码")
    private String passWord;//密码
    @Schema(title ="创建时间",description="创建时间")
    private LocalDateTime createTime;//创建时间
    @Schema(title ="更新时间",description="更新时间")
    private LocalDateTime dateTime;//更新时间
}
