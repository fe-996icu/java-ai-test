package com.icu.mybatis.utils;

import org.springframework.stereotype.Component;

/**
 * 模拟第三方SDK所需的参数
 */
@Component
public class MockThirdSdkParameter {
    public void sayHello() {
        System.out.println("hello MockThirdSdkParameter");
    }
}
