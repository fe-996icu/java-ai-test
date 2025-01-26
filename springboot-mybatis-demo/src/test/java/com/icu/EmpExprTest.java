package com.icu;

import com.icu.mybatis.pojo.EmpExpr;
import com.icu.mybatis.pojo.Employee;
import com.icu.mybatis.vo.employee.EmployeeSaveVo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmpExprTest {
    @Test
    public void testCreate() {
        List<EmpExpr> exprList =new ArrayList<>();
        exprList.add(new EmpExpr(
                1,
                1,
                LocalDate.parse("2020-01-01"),
                LocalDate.parse("2020-01-01"),
                "company",
                "job"));
        EmployeeSaveVo vo = new EmployeeSaveVo(
                1,
                "name",
                (byte) 1,
                "phone",
                LocalDate.parse("2020-01-01"),
                1,
                1,
                LocalDate.parse("2020-01-01"),
                LocalDateTime.now(),
                (byte) 1,
                exprList
        );
        System.out.println(vo);
    }
}
