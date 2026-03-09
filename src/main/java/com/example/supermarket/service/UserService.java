package com.example.supermarket.service;

import com.example.supermarket.common.DTO.UserDto;
import com.example.supermarket.common.VO.LoginInfo;
import com.example.supermarket.common.entity.Result;
import com.example.supermarket.common.entity.UserEntity;

public interface UserService {

    /**
     * 用户登录
     * @param user
     * @return
     */
     Result login(UserDto user);
}
