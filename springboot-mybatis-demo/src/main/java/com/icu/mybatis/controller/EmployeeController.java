package com.icu.mybatis.controller;

import com.icu.mybatis.pojo.Employee;
import com.icu.mybatis.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    @ResponseBody
    public List<Employee> page(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        System.out.println(String.format("page: %s, pageSize: %s", page, pageSize));

        log.info("page: {}, pageSize: {}", page, pageSize);

        return employeeService.page(page, pageSize);
    }

    @GetMapping("all")
    public List<Employee> findAll(Integer page, Integer pageSize) {
        System.out.println(String.format("page: %s, pageSize: %s", page, pageSize));

        List<Employee> list = employeeService.findAll(page, pageSize);
        System.out.println(list.get(0));
        return list;
    }
}

