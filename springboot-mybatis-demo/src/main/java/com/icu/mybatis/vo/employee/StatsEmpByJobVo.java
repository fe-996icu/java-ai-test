package com.icu.mybatis.vo.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsEmpByJobVo {
    private Integer jobId;
    private String jobName;
    private Integer total;
}
