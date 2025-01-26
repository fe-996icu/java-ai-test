package com.icu.mybatis.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestController {
    @RequestMapping("test")
    // 不设置 @DateTimeFormat 使用“-”分隔符和“/”分隔符都能转换成功
    public String test(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                       @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dateTime) {
        System.out.println(date);
        System.out.println(dateTime);
        return null;
    }
}
