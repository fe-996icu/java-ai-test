package com.icu.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz {
    private Integer id;
    private String name;
    private String room;
    private Integer employeeId;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Byte status;
    private LocalDateTime lastUpdateTime;
    private LocalDateTime createTime;
}
