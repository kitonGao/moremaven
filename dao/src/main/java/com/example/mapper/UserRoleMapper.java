package com.example.mapper;

import com.example.model.UserRole;
import com.example.model.UserRoleExample;
import tk.mybatis.mapper.common.Mapper;

public interface UserRoleMapper extends Mapper<UserRole> {
    long countByExample(UserRoleExample example);
}