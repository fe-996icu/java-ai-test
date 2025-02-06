package com.icu.mybatisplus.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true) // 继承父类的equals和hashCode方法
@Data
public class UserListQuery extends PageQuery {
    private String username;
    private Double minBalance;
    private Double maxBalance;
    private Integer gender;
    private Integer status;
}
