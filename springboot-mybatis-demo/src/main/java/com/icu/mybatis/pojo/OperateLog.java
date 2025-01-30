package com.icu.mybatis.pojo;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class OperateLog {
    private Integer id;
    private Integer operateId;
    private LocalDateTime operateTime;
    private String className;
    private String methodName;
    private String methodArgs;
    private String methodResult;
    private Integer costTime;
    private Integer operateType;
}