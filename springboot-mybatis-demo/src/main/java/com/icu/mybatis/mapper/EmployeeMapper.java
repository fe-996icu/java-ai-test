package com.icu.mybatis.mapper;

import com.icu.mybatis.pojo.EmpExpr;
import com.icu.mybatis.pojo.Employee;
import com.icu.mybatis.vo.employee.StatsEmpByBirthdayVo;
import com.icu.mybatis.vo.employee.StatsEmpByGenderVo;
import com.icu.mybatis.vo.employee.StatsEmpByJobVo;
import com.icu.mybatis.vo.employee.EmployeeRequestPageVo;
import com.icu.mybatis.vo.login.LoginParamVo;
import com.icu.mybatis.vo.login.LoginResultVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // 应用程序在运行时，会自动的为该接口创建一个实现类对象(代理对象)，并且会自动将该实现类对象存入 IOC 容器 - bean
public interface EmployeeMapper {
    @Select("select * from employee")
    List<Employee> findAll();

    @Select("select id, username, name, gender, phone, birthday, department_id, job_id, join_date, update_date, status from employee where id = #{id}")
    Employee findById(Integer id);

    // table (xxx, xxx, ...) 里写的是数据库字段
    // values (#{xxx}, #{xxx}, ...) 里写的是实体类属性
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into employee (username, password, name, gender, phone, birthday, department_id, job_id, join_date, update_date, status) " +
            "values (#{username}, #{password}, #{name}, #{gender}, #{phone}, #{birthday}, #{departmentId}, #{jobId}, #{joinDate}, #{updateDate}, #{status})")
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

    List<Employee> findByStatus(int[] statusList);

    /**
     * 插入一条员工工作经历
     * @param empExpr
     */
    // @Options(useGeneratedKeys = true, keyProperty = "id")
    // @Insert("insert into emp_expr (employee_id, begin_date, end_date, company, job) values (#{employeeId}, #{beginDate}, #{endDate}, #{company}, #{job})")
    void saveEmpExpr(EmpExpr empExpr);

    /**
     * 批量插入员工工作经历
     * @param exprList
     */
    void saveEmpExprList(@Param("list") List<EmpExpr> exprList);

    List<StatsEmpByJobVo> statsEmpOfJob();

    List<StatsEmpByGenderVo> statsEmpOfGender();

    List<StatsEmpByBirthdayVo> statsEmpOfBirthday();

    LoginResultVo login(LoginParamVo param);
}