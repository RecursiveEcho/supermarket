package com.example.supermarket.service.impl;

import Utils.JwtUtils;
import com.example.supermarket.common.VO.LoginInfo;
import com.example.supermarket.common.entity.UserEntity;
import com.example.supermarket.mapper.UserMapper;
import com.example.supermarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public LoginInfo login(UserEntity user) {
        UserEntity u = userMapper.login(user);
        if (u != null) {
            String token = JwtUtils.generateJwt(u.getId(), u.getUserName());
            return new LoginInfo(u.getId(),u.getUserName(),token);
        }else return null;
    }
}
