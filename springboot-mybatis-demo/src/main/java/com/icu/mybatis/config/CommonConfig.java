package com.icu.mybatis.config;

import com.icu.mybatis.utils.MockThirdSdk;
import com.icu.mybatis.utils.MockThirdSdkParameter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CommonConfig {
    // 使用 @Bean 注解来创建第三方sdk提供的类并交给 Spring IOC 管理
    // 如果第三方类的构造函数需要参数，可以通过形参传递，形参的类必须是 bean 对象，否则会报错
    // 如果 @Bean 注解的 value 属性值为空，则默认使用方法名作为 bean 的 名字
    @Bean(name = "mockThirdSdk2")
    public MockThirdSdk mockThirdSdk(MockThirdSdkParameter mockThirdSdkParameter) {
        return new MockThirdSdk(mockThirdSdkParameter);
    }
}
