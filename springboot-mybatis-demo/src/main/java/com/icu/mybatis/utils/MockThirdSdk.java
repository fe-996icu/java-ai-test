package com.icu.mybatis.utils;

/**
 * 模拟第三方SDK
 */
public class MockThirdSdk {
    private final MockThirdSdkParameter mockThirdSdkParameter;

    public MockThirdSdk(MockThirdSdkParameter mockThirdSdkParameter) {
        this.mockThirdSdkParameter = mockThirdSdkParameter;
    }

    public void sayHello() {
        mockThirdSdkParameter.sayHello();
        // System.out.println("hello MockThirdSdk");
    }
}
