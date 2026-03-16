package com.example.supermarket.controller;

import com.example.supermarket.pojo.DTO.UserDto;
import com.example.supermarket.pojo.entity.Result;
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

    @Operation(summary = "用户登陆",description = "用户登陆接口")
    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto){
        return userService.login(userDto);
    }

}
