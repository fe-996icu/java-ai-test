package com.icu.mybatis.vo.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsEmpByBirthdayVo {
    private String year;
    private Integer num;
}
