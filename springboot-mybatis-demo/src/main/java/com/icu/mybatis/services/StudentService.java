package com.icu.mybatis.services;

import com.icu.mybatis.pojo.Student;
import com.icu.mybatis.vo.student.StatsStudentOfClazz;
import com.icu.mybatis.vo.student.StatsStudentOfEdu;
import com.icu.mybatis.vo.student.StudentPageVo;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);
    void deleteStudent(Integer id);
    void updateStudent(Student student);
    List<Student> page(StudentPageVo param);
    List<StatsStudentOfClazz> statsStudentOfClazz();

    List<StatsStudentOfEdu> statsStudentOfEdu();
}
