package com.icu;

import com.icu.mybatis.mapper.EmployeeMapper;
import com.icu.mybatis.pojo.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest // SpringBoot 单元测试的注解 - 当前测试类中的测试方法运行时，会启动 springboot 项目 - IOC 容器
class SpringbootMybatisDemoApplicationTests {
	@Autowired
	private EmployeeMapper employeeMapper;

	@Test // junit 测试注解
	public void findAll() {
		List<Employee> list = employeeMapper.findAll();
		list.forEach(System.out::println);
	}

}
