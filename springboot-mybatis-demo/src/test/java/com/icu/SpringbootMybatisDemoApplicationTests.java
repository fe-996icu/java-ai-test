package com.icu;

import com.icu.mybatis.mapper.EmployeeMapper;
import com.icu.mybatis.pojo.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

	@Test
	public void saveEmployee() {
		Employee employee = new Employee();
		employee.setName("李时珍");
		employee.setGender((byte) 0);
		employee.setPhone("12345678901");
		employee.setBirthday(LocalDate.parse("1990-01-01"));
		employee.setDeptId(1);
		employee.setJobId(1);
		employee.setJoinDate(LocalDate.parse("2020-01-01"));
		employee.setUpdateDate(LocalDateTime.parse("2020-01-01T00:00:00"));
		employee.setStatus((byte) 1);

		System.out.println(employee);
		boolean result = employeeMapper.saveEmployee(employee);
		System.out.println("执行结果 = " + result);
	}

	@Test
	public void findByIdOfXml() {
		Employee employee = employeeMapper.findByIdOfXml(1);
		System.out.println(employee);
	}

	@Test
	public void updateEmployeeOfXml() {
		List<Employee> list = employeeMapper.findAll();
		// 取出最后一个
		Employee employee = list.get(list.size() - 1);
		// 修改名字
		int i = (int)(Math.random()*10);
		employee.setName("name updated(" + i + ")");
		employee.setUpdateDate(LocalDateTime.now());
		System.out.println("修改 employee 表中最后一条数据 :" + employee);

		boolean result = employeeMapper.updateEmployeeOfXml(employee);
		System.out.println("执行结果 = " + result);
	}
}
