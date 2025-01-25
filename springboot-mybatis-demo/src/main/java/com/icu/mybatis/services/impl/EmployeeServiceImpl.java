package com.icu.mybatis.services.impl;

import com.github.pagehelper.PageHelper;
import com.icu.mybatis.mapper.EmployeeMapper;
import com.icu.mybatis.pojo.Employee;
import com.icu.mybatis.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> page(Integer page, Integer pageSize) {
        List<Employee> list = null;
        // 设置分页参数
        PageHelper.startPage(page, pageSize);
        // 调用mapper接口的分页查询方法（PageHelper拦截并处理的mybatis返回值是一个Page对象，继承了List接口）
        list = employeeMapper.pageNew();

        return list;
    }

    @Override
    public List<Employee> findAll() {
        return employeeMapper.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        return employeeMapper.findById(id);
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeMapper.saveEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeMapper.deleteEmployee(id);
    }

    @Override
    public int total() {
        return employeeMapper.total();
    }
}
