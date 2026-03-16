package com.example.supermarket.mapper;

import com.example.supermarket.pojo.DTO.UserDto;
import com.example.supermarket.pojo.entity.UserEntity;
import  org.apache.ibatis.annotations.Mapper;

/**
 * 管理者登陆接口
 * @author chen.yang
 * @date 2026-03-13 09:47:56
 */

@Mapper
public interface UserMapper {

    /**
     * 管理者登陆
     * @param user
     * @return
     */
    UserEntity login(UserDto user);
}
