package com.icu.mybatisplus.dto;

import lombok.Data;

import java.util.List;

// 封装返回给前端的统一分页对象，因为可能会提供给其他微服务使用，所以封装在dto包里
@Data
public class PageDTO <T> {
    // 总页数
    private Long pages;
    // 总条数
    private Long total;
    // 集合
    private List<T> rows;
}
