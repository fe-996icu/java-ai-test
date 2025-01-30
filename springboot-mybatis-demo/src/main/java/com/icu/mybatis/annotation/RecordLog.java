package com.icu.mybatis.annotation;

import com.icu.mybatis.enums.OperateLogType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 记录操作日志注解，增加到方法上，方法执行时会向日志表中增加记录
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RecordLog {
    /**
     * 操作日志类型，注解参数
     * @return
     */
    OperateLogType value();
}
