package com.icu.mybatisplus;

import com.icu.mybatisplus.mapper.UserMapper;
import com.icu.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootMybatisplusDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println("Hello World");
    }

    @Test
    public void testSelect(){
        User user = userMapper.selectById(1);
        System.out.println(user);
        System.out.println(userMapper);
    }
}
