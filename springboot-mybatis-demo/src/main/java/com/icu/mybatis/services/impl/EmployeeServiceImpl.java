package com.icu.mybatis.services.impl;

import com.github.pagehelper.PageHelper;
import com.icu.mybatis.mapper.EmployeeMapper;
import com.icu.mybatis.pojo.EmpExpr;
import com.icu.mybatis.pojo.Employee;
import com.icu.mybatis.services.EmployeeService;
import com.icu.mybatis.vo.employee.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> page(EmployeeRequestPageVo param) {
        // 设置分页参数
        PageHelper.startPage(param.getPage(), param.getPageSize());
        // 调用mapper接口的分页查询方法（PageHelper拦截并处理的mybatis返回值是一个Page对象，继承了List接口）
        List<Employee> list = employeeMapper.pageNew(param);
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

    public List<Employee> findByStatus(int[] statusList) {
        return employeeMapper.findByStatus(statusList);
    }

    @Transactional(rollbackFor = { Exception.class }) // 事务管理-默认出现运行时异常 RuntimeException 才会回滚
    @Override
    public void saveEmployeeAndExpr(EmployeeSaveVo saveVo) {
        Employee emp = new Employee(
                saveVo.getId(),
                saveVo.getName(),
                saveVo.getGender(),
                saveVo.getPhone(),
                saveVo.getBirthday(),
                saveVo.getDeptId(),
                saveVo.getJobId(),
                saveVo.getJoinDate(),
                saveVo.getUpdateDate(),
                saveVo.getStatus()
        );
        // 插入员工信息到数据库
        employeeMapper.saveEmployee(emp);
        saveVo.setId(emp.getId());

        // double d = 1/0;

        // 设置提交的工作经历关联的员工id
        for (EmpExpr expr : saveVo.getExprList()) {
            expr.setEmpId(emp.getId());
            // employeeMapper.saveEmpExpr(expr);
            // System.out.println("该员工工作经历入库后的ID：-----------------"+expr.getId());
        }
        // 批量插入工作经历
        employeeMapper.saveEmpExprList(saveVo.getExprList());
        saveVo.getExprList().forEach(expr -> {
            System.out.println("该员工工作经历入库后的ID：-----------------"+expr.getId());
        });
    }

    @Override
    public List<StatsEmpByJobVo> statsEmpOfJob() {
        return employeeMapper.statsEmpOfJob();
    }

    @Override
    public List<StatsEmpByGenderVo> statsEmpOfGender() {
        return employeeMapper.statsEmpOfGender();
    }

    @Override
    public List<StatsEmpByBirthdayVo> statsEmpOfBirthday() {
        return employeeMapper.statsEmpOfBirthday();
    }
}
