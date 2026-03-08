package com.example.supermarket.service;

import com.example.supermarket.entity.LoginInfo;
import com.example.supermarket.entity.User_entity;

public interface UserService {

    /**
     * 用户登录
     * @param user
     * @return
     */
     LoginInfo login(User_entity user);
}
