package com.icu.mybatisplus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    DISABLED(0, "禁用"),
    NORMAL(1, "正常"),
    FREEZE(2, "冻结"),
    ;

    @JsonValue // 告诉jackson，序列化时，返回给前端的value，因为springboot用的是jackson
    @EnumValue // 告诉MybatisPlus，数据库存的是value
    private final Integer value;
    private final String description;
}
