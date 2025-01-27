package com.icu.mybatis.services;

import com.icu.mybatis.pojo.Employee;
import com.icu.mybatis.vo.employee.*;

import java.util.List;

public interface EmployeeService {
    List<Employee> page(EmployeeRequestPageVo param);

    List<Employee> findAll();

    Employee findById(Integer id);

    void saveEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Integer id);

    int total();

    List<Employee> findByStatus(int[] statusList);

    void saveEmployeeAndExpr(EmployeeSaveVo employee);

    List<StatsEmpByJobVo> statsEmpOfJob();

    List<StatsEmpByGenderVo> statsEmpOfGender();

    List<StatsEmpByBirthdayVo> statsEmpOfBirthday();
}
