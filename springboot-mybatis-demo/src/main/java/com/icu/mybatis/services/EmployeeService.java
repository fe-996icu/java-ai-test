package com.icu.mybatis.services;

import com.icu.mybatis.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> page(Integer page, Integer pageSize);

    List<Employee> findAll(Integer page, Integer pageSize);
}
