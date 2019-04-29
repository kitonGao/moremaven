package com.example.tool;

import com.example.util.DomainCheckType;
import com.example.util.PageListDto;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BaseService<T> {

    void checkDomain(DomainCheckType checkType, T t)throws Exception;

    T selectByPrimaryKey(Object o);

    T insert(T t)throws Exception;

    int updateByPrimaryKey(T t)throws Exception;

    <ExampleDto> ExampleDto getExampleByDto(T t);

    PageListDto<T> selectByExampleWithRowBounds(T t, RowBounds bounds);

    PageListDto<T> selectByExampleWithRowBounds(T t, RowBounds bounds, String orderByCase);

    List<T> selectByExample(T t);

    List<T> selectByExample(T t, String orderByCase);

}
