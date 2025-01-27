package com.icu.mybatis.vo.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsEmpByGenderVo {
    private String gender;
    private Integer num;
}
