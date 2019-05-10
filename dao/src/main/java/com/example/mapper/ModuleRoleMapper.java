package com.example.mapper;

import com.example.model.ModuleRole;
import com.example.model.ModuleRoleExample;
import tk.mybatis.mapper.common.Mapper;

public interface ModuleRoleMapper extends Mapper<ModuleRole> {
    long countByExample(ModuleRoleExample example);
}