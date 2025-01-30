package com.icu.mybatis.enums;

public enum OperateLogType {
    ADD(1, "新增"),
    DELETE(2, "删除"),
    UPDATE(3, "修改"),
    SELECT(4, "查询");

    private final Integer value;
    private final String description;

    private OperateLogType(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return this.value;
    }
    public String getDescription() {
        return this.description;
    }
}
