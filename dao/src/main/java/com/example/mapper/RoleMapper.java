package com.example.mapper;

import com.example.model.Role;
import com.example.model.RoleExample;
import tk.mybatis.mapper.common.Mapper;

public interface RoleMapper extends Mapper<Role> {
    long countByExample(RoleExample example);
}