package com.icu.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpExpr {
    private Integer id;
    private Integer employeeId;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String company;
    private String job;
}
