package com.icu.mybatis.utils;

import org.springframework.stereotype.Component;

@Component
public class CommonHelper {
    public static String getUniqueId() {
        return java.util.UUID.randomUUID().toString();
    }
}
