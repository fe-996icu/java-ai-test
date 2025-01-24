package com.icu.mybatis.services.impl;

import com.icu.mybatis.mapper.EmployeeMapper;
import com.icu.mybatis.pojo.Employee;
import com.icu.mybatis.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> page(Integer page, Integer pageSize) {
        List<Employee> list = null;

        list = employeeMapper.page(page, pageSize);

        return list;
    }

    @Override
    public List<Employee> findAll(Integer page, Integer pageSize) {
        return employeeMapper.findAll();
    }
}
