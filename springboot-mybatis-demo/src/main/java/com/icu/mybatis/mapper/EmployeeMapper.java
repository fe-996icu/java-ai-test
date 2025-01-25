package com.icu.mybatis.mapper;

import com.icu.mybatis.pojo.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

@Mapper // 应用程序在运行时，会自动的为该接口创建一个实现类对象(代理对象)，并且会自动将该实现类对象存入 IOC 容器 - bean
public interface EmployeeMapper {
    @Select("select * from employee")
    public List<Employee> findAll();

    @Select("select id, name, gender, phone, birthday, dept_id, job_id, join_date, update_date, status from employee where id = #{id}")
    public Employee findById(Integer id);

    // table (xxx, xxx, ...) 里写的是数据库字段
    // values (#{xxx}, #{xxx}, ...) 里写的是实体类属性
    @Insert("insert into employee (name, gender, phone, birthday, dept_id, job_id, join_date, update_date, status) " +
            "values (#{name}, #{gender}, #{phone}, #{birthday}, #{deptId}, #{jobId}, #{joinDate}, #{updateDate}, #{status})")
    public boolean saveEmployee(Employee employee);

    public Employee findByIdOfXml(Integer id);

    // 这里可以设置boolean，也可以设置int，因为MyBatis会自动判断返回值类型，并返回受影响的行数
    public boolean updateEmployeeOfXml(Employee employee);

    @Select("select * from employee limit #{page}, #{pageSize}")
    public List<Employee> page(Integer page, Integer pageSize);

    void updateEmployee(Employee employee);

    void deleteEmployee(Integer id);

    @Select("select count(*) from employee")
    int total();
}