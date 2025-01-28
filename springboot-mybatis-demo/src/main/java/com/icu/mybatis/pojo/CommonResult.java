package com.icu.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> CommonResult<T> Success(T data) {
        return new CommonResult<>(1, "success", data);
    }

    public static <T> CommonResult<T> Fail() {
        return new CommonResult<>(0, "fail", null);
    }

    public static <T> CommonResult<T> Fail(String msg) {
        return new CommonResult<>(0, msg, null);
    }

    public static <T> CommonResult<T> Fail(Integer code, String msg) {
        return new CommonResult<>(code, msg, null);
    }
}
