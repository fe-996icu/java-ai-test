package com.icu.mybatis.controller;

import com.icu.mybatis.common.Result;
import com.icu.mybatis.pojo.CommonResult;
import com.icu.mybatis.pojo.Employee;
import com.icu.mybatis.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result<Map<String, Object>> page(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        log.info("page: {}, pageSize: {}", page, pageSize);

        List<Employee> list = employeeService.page(page, pageSize);
        int total = employeeService.total();

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", list);
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
    public Result<Null> saveEmployee(@RequestBody Employee employee){
        log.info("新增员工api：{}", employee);
        employeeService.saveEmployee(employee);
        return Result.ok(null);
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

    @GetMapping("all")
    public List<Employee> findAll() {
        log.info("查询所有员工api");
        List<Employee> list = employeeService.findAll();
        return list;
    }
}

