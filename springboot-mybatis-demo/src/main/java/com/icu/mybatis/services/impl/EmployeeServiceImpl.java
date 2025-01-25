package com.icu.mybatis.services.impl;

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
        int start = (page - 1) * pageSize;

        list = employeeMapper.page(start, pageSize);

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
