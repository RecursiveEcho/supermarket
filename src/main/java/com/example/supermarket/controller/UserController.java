package com.example.supermarket.controller;

import com.example.supermarket.common.DTO.UserDto;
import com.example.supermarket.common.VO.LoginInfo;
import com.example.supermarket.common.entity.Result;
import com.example.supermarket.common.entity.UserEntity;
import com.example.supermarket.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name="用户登陆",description="用户登陆接口")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登陆
     * @param userDto
     * @return
     */
    @Operation(summary = "用户登陆",description = "用户登陆接口")
    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto){
        try {
            log.info("用户登陆");
            return userService.login(userDto);
        } catch (Exception e) {
            return Result.error("用户登陆失败"+e.getMessage());
        }
    }

}
