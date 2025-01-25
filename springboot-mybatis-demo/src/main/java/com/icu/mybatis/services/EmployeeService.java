package com.icu.mybatis.services;

import com.icu.mybatis.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> page(Integer page, Integer pageSize);

    List<Employee> findAll();

    Employee findById(Integer id);

    void saveEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Integer id);

    int total();
}
