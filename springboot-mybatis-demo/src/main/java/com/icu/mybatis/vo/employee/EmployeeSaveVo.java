package com.icu.mybatis.vo.employee;

import com.icu.mybatis.pojo.EmpExpr;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSaveVo {
    private Integer id;
    private String name;
    private Byte gender;
    private String phone;
    private LocalDate birthday;
    private Integer departmentId;
    private Integer jobId;
    private LocalDate joinDate;
    private LocalDateTime updateDate;
    private Byte status;

    private List<EmpExpr> exprList;
}
