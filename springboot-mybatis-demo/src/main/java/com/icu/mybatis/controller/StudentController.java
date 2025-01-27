package com.icu.mybatis.controller;

import com.github.pagehelper.Page;
import com.icu.mybatis.common.Result;
import com.icu.mybatis.pojo.Student;
import com.icu.mybatis.services.StudentService;
import com.icu.mybatis.vo.student.StatsStudentOfClazz;
import com.icu.mybatis.vo.student.StudentPageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 添加学生信息
     * @param student
     * @return
     */
    @PostMapping
    public Result<Integer> addStudent(@RequestBody Student student) {
        log.info("新增学生接口，请求参数：{}", student);
        studentService.addStudent(student);
        log.info("新增学生接口，返回结果：{}", student.getId());
        return Result.ok(student.getId());
    }

    @DeleteMapping("/{id}")
    public Result deleteStudent(@PathVariable(required = true) Integer id) {
        log.info("删除学生接口：{}", id);
        studentService.deleteStudent(id);
        log.info("删除学生成功");
        return Result.ok(null);
    }

    @PutMapping
    public Result updateStudent(@RequestBody Student student) {
        log.info("修改学生接口-参数：{}", student);
        studentService.updateStudent(student);
        log.info("修改学生成功-：{}", student.getId());
        return Result.ok(null);
    }

    /**
     * 分页查询学生信息
     * @return
     */
    @GetMapping()
    public Result<Map<String, Object>> page(StudentPageVo param) {
        log.info("分页查询学生接口：{}", param);
        List<Student> list = studentService.page(param);
        Page<Student> page = (Page<Student>) list;
        log.info("分页查询学生接口，返回结果：{}", list);
        Map<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("rows", page);
        return Result.ok(map);
    }

    @GetMapping({"/stats", "/stats/{type}"})
    public Result<List> stats(@PathVariable(value = "type", required = false) String type) {
        log.info("统计学生api：{}", type);

        List list = null;
        if ("clazz".equals(type)) {
            list = studentService.statsStudentOfClazz();
        } else if ("edu".equals(type)) {
            list = studentService.statsStudentOfEdu();
        } else {
            list = studentService.statsStudentOfClazz();
        }

        return Result.ok(list);
    }
}
