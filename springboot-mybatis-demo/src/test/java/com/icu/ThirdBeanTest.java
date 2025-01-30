package com.icu;

import com.icu.mybatis.utils.MockThirdSdk;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ThirdBeanTest {
    @Autowired
    private MockThirdSdk mockThirdSdk;
    @Test
    public void testThirdBean() {
        mockThirdSdk.sayHello();
        System.out.println("xxx");
    }
}
