package com.icu.mybatis.controller;

import com.github.pagehelper.Page;
import com.icu.mybatis.common.Result;
import com.icu.mybatis.pojo.EmpExpr;
import com.icu.mybatis.pojo.Employee;
import com.icu.mybatis.services.EmployeeService;
import com.icu.mybatis.vo.employee.EmployeeRequestPageVo;
import com.icu.mybatis.vo.employee.EmployeeSaveVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.FormatFlagsConversionMismatchException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    @ResponseBody
    public Result<Map<String, Object>> page(EmployeeRequestPageVo param) {
        log.info("分页接口查询参数：{}", param);
        if(param.getPage()==null) param.setPage(1);
        if(param.getPageSize()==null) param.setPageSize(10);

        // PageHelper拦截并处理的mybatis返回值是一个Page对象，继承了List接口，所以可以强转成Page对象
        List<Employee> list = employeeService.page(param);
        Page<Employee> pageEmp = (Page<Employee>) list;

        Map<String, Object> map = new HashMap<>();
        map.put("total", pageEmp.getTotal()); // 获取总记录数
        map.put("rows", pageEmp); // 获取分页数据
        log.info("返回结果：{}", map);
        return Result.ok(map);
    }

    @GetMapping("/{id}")
    public Result<Employee> findById(@PathVariable(required = true) Integer id) {
        log.info("查询员工api：{}", id);
        Employee employee = employeeService.findById(id);
        return Result.ok(employee);
    }

    @PostMapping()
    public Result<Integer> saveEmployee(@RequestBody Employee employee){
        log.info("新增员工api：{}", employee);
        employeeService.saveEmployee(employee);
        log.info("该员工入库后的ID：{}", employee.getId());
        return Result.ok(employee.getId());
    }


    @PostMapping("/emp_expr")
    public Result<Integer> saveEmployeeAndExpr(@RequestBody EmployeeSaveVo employee){
        log.info("新增员工和工作经历api：{}", employee);
        employeeService.saveEmployeeAndExpr(employee);
        log.info("该员工入库后的ID：{}", employee.getId());
        for (EmpExpr expr : employee.getExprList()){
            log.info("该员工工作经历入库后的ID：{}", expr.getId());
        }
        return Result.ok(employee.getId());
    }

    @PutMapping()
    public Result<Null> updateEmployee(@RequestBody Employee employee){
        log.info("修改员工api：{}", employee);
        employeeService.updateEmployee(employee);
        return Result.ok(null);
    }

    @DeleteMapping("/{id}")
    public Result<Null> deleteEmployee(@PathVariable(required = true) Integer id) {
        log.info("删除员工api：{}", id);
        employeeService.deleteEmployee(id);
        return Result.ok(null);
    }

    @GetMapping("/status")
    public Result<List<Employee>> findByStatus(@RequestParam(required = true, value = "status") int[] statusList) {
        log.info("根据状态查询员工api：{}", statusList);
        List<Employee> list = employeeService.findByStatus(statusList);
        return Result.ok(list);
    }

    @GetMapping("all")
    public List<Employee> findAll() {
        log.info("查询所有员工api");
        List<Employee> list = employeeService.findAll();
        return list;
    }
}

