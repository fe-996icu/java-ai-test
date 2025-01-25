package com.icu.mybatis.mapper;

import com.icu.mybatis.pojo.Employee;
import com.icu.mybatis.vo.employee.EmployeeRequestPageVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // 应用程序在运行时，会自动的为该接口创建一个实现类对象(代理对象)，并且会自动将该实现类对象存入 IOC 容器 - bean
public interface EmployeeMapper {
    @Select("select * from employee")
    List<Employee> findAll();

    @Select("select id, name, gender, phone, birthday, dept_id, job_id, join_date, update_date, status from employee where id = #{id}")
    Employee findById(Integer id);

    // table (xxx, xxx, ...) 里写的是数据库字段
    // values (#{xxx}, #{xxx}, ...) 里写的是实体类属性
    @Insert("insert into employee (name, gender, phone, birthday, dept_id, job_id, join_date, update_date, status) " +
            "values (#{name}, #{gender}, #{phone}, #{birthday}, #{deptId}, #{jobId}, #{joinDate}, #{updateDate}, #{status})")
    boolean saveEmployee(Employee employee);

    Employee findByIdOfXml(Integer id);

    // 这里可以设置boolean，也可以设置int，因为MyBatis会自动判断返回值类型，并返回受影响的行数
    public boolean updateEmployeeOfXml(Employee employee);

    /**
     * 手工写的分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @Select("select * from employee limit #{page}, #{pageSize}")
    List<Employee> page(Integer page, Integer pageSize);

    /**
     * 使用PageHelper实现的分页查询
     * @return
     */
    // @Select("select * from employee") // 使用PageHelper做分页，sql语句结尾不能有分号
    List<Employee> pageNew(EmployeeRequestPageVo param);

    void updateEmployee(Employee employee);

    void deleteEmployee(Integer id);


    /**
     * 查询员工总数
     */
    @Select("select count(*) from employee")
    int total();
}