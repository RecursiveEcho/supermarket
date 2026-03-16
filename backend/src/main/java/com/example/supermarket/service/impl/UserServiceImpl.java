package com.example.supermarket.service.impl;

import Utils.JwtUtils;
import com.example.supermarket.pojo.DTO.UserDto;
import com.example.supermarket.pojo.VO.LoginInfo;
import com.example.supermarket.pojo.entity.Result;
import com.example.supermarket.pojo.entity.UserEntity;
import com.example.supermarket.mapper.UserMapper;
import com.example.supermarket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登陆
     * @param user
     * @return
     */
    @Override
    public Result login(UserDto user) {
        log.info("用户登陆,用户名:{}",user.getUserName());
        UserEntity u = userMapper.login(user);
        if (u != null) {
            String token = JwtUtils.generateJwt(u.getId(), u.getUserName());
            LoginInfo loginInfo = new LoginInfo(u.getId(),u.getUserName(),token);
            return Result.success(loginInfo);
        }else return Result.error("用户名或密码错误");
    }
}
