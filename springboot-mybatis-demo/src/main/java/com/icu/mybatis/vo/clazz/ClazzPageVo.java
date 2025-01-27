package com.icu.mybatis.vo.clazz;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ClazzPageVo {
    private Integer page = 1;
    private Integer pageSize = 2;
    private String name;
    private String room;
    private Integer employeeId;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Byte status;
}
