package com.icu.mybatis.mapper;

import com.icu.mybatis.pojo.Student;
import com.icu.mybatis.vo.student.StudentPageVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    void addStudent(Student clazz);

    void deleteStudent(Integer id);

    void updateStudent(Student clazz);

    List<Student> page(StudentPageVo param);

}