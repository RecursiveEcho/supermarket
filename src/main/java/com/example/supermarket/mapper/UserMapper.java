package com.example.supermarket.mapper;

import com.example.supermarket.common.DTO.UserDto;
import com.example.supermarket.common.entity.UserEntity;
import  org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select id, user_name, pass_word, create_time, update_time,token from user where user_name=#{userName} and pass_word=#{passWord}")
    UserEntity login(UserDto user);
}
