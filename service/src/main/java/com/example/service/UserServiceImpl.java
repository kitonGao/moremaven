package com.example.service;


import com.example.model.User;
import com.example.serviceImpl.IUserService;
import com.example.dto.DomainCheckType;
import com.example.dto.PageListDto;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {


    @Override
    public void checkDomain(DomainCheckType checkType, User user) throws Exception {

    }

    @Override
    public User selectByPrimaryKey(Object o) {
        return null;
    }

    @Override
    public User insert(User user) throws Exception {
        return null;
    }

    @Override
    public int updateByPrimaryKey(User user) throws Exception {
        return 0;
    }

    @Override
    public <ExampleDto> ExampleDto getExampleByDto(User user) {
        return null;
    }

    @Override
    public PageListDto<User> selectByExampleWithRowBounds(User user, RowBounds bounds) {
        return null;
    }

    @Override
    public PageListDto<User> selectByExampleWithRowBounds(User user, RowBounds bounds, String orderByCase) {
        return null;
    }

    @Override
    public List<User> selectByExample(User user) {
        return null;
    }

    @Override
    public List<User> selectByExample(User user, String orderByCase) {
        return null;
    }
}
