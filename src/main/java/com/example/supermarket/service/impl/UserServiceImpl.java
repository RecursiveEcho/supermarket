package com.example.supermarket.service.impl;

import Util.JwtUtils;
import com.example.supermarket.entity.LoginInfo;
import com.example.supermarket.entity.User_entity;
import com.example.supermarket.mapper.UserMapper;
import com.example.supermarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public LoginInfo login(User_entity user) {
        User_entity u = userMapper.login(user);
        if (u != null) {
            String token = JwtUtils.generateJwt(u.getId(), u.getUserName());
            return new LoginInfo(u.getId(),u.getUserName(),token);
        }else return null;
    }
}
