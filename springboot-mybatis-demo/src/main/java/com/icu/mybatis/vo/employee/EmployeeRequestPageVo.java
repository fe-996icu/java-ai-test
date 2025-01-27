package com.icu.mybatis.vo.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestPageVo {
    private Integer page;
    private Integer pageSize;
    private String name;
    private Integer departmentId;
    private Integer jobId;
    private Integer status;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginBirthday;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endBirthday;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginJoinDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endJoinDate;
}
