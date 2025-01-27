package com.icu.mybatis.services.impl;

import com.github.pagehelper.PageHelper;
import com.icu.mybatis.mapper.StudentMapper;
import com.icu.mybatis.pojo.Student;
import com.icu.mybatis.services.StudentService;
import com.icu.mybatis.vo.student.StudentPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void addStudent(Student student) {
        studentMapper.addStudent(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentMapper.deleteStudent(id);
    }

    @Override
    public void updateStudent(Student student) {
        student.setLastUpdateTime(LocalDateTime.now());
        studentMapper.updateStudent(student);
    }

    @Override
    public List<Student> page(StudentPageVo param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        return studentMapper.page(param);
    }
}
