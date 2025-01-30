package com.icu.mybatis.utils;

import com.icu.mybatis.vo.login.LoginResultVo;

public class ThreadLocalHelper {
    private static final ThreadLocal<LoginResultVo> CURRENT_LOCAL = new ThreadLocal<>();

    public static void set(LoginResultVo value) {
        CURRENT_LOCAL.set(value);
    }

    public static LoginResultVo get() {
        return CURRENT_LOCAL.get();
    }

    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}
