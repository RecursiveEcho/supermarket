package com.example.supermarket.service;

import com.example.supermarket.pojo.DTO.UserDto;
import com.example.supermarket.pojo.entity.Result;

public interface UserService {

    /**
     * 用户登录
     * @param user
     * @return
     */

     Result login(UserDto user);
}
