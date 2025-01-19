package com.icu.mybatis.mapper;

import com.icu.mybatis.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper // 应用程序在运行时，会自动的为该接口创建一个实现类对象(代理对象)，并且会自动将该实现类对象存入 IOC 容器 - bean
public interface EmployeeMapper {
    @Select("select * from employee")
    public List<Employee> findAll();
}
