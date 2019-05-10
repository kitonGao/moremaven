package com.example.mapper;

import com.example.model.User;
import com.example.model.UserExample;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    long countByExample(UserExample example);
}