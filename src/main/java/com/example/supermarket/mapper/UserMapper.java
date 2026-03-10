package com.example.supermarket.mapper;

import com.example.supermarket.common.DTO.UserDto;
import com.example.supermarket.common.entity.UserEntity;
import  org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    UserEntity login(UserDto user);
}
