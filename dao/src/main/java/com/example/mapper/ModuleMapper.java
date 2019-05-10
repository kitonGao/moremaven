package com.example.mapper;

import com.example.model.Module;
import com.example.model.ModuleExample;
import tk.mybatis.mapper.common.Mapper;

public interface ModuleMapper extends Mapper<Module> {
    long countByExample(ModuleExample example);
}