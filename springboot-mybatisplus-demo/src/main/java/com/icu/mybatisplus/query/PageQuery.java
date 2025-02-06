package com.icu.mybatisplus.query;

import lombok.Data;

// 封装统一的分页查询请求参数实体，各业务实体的查询参数需要做分页的继承此实体即可。
@Data
public class PageQuery {
    private Integer pageNum;
    private Integer pageSize;
    private String orderBy;
    private Boolean isAsc;
}
