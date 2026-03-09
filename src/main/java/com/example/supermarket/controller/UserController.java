package com.example.supermarket.controller;

import com.example.supermarket.common.VO.LoginInfo;
import com.example.supermarket.common.entity.Result;
import com.example.supermarket.common.entity.UserEntity;
import com.example.supermarket.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name="用户登陆",description="用户登陆接口")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "用户登陆",description = "用户登陆接口")
    @PostMapping("/login")
    public Result login(@RequestBody UserEntity user){
        log.info("用户登陆");
        LoginInfo info=userService.login(user);
        if(info!=null){
            log.info("用户登陆成功");
            return Result.success(info);
        }else {
            return Result.error("用户名或密码错误");
        }
    }
}
