package com.icu.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.icu.mybatisplus.mapper.UserMapper;
import com.icu.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
        // userMapper.selectList(null)
        System.out.println(user);
        System.out.println(userMapper);
        System.out.println("-----------------------------------");
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user = new User();
        // user.setId(null);
        user.setUsername("admin23");
        user.setPassword("123456");
        user.setName("管理员");
        user.setBirthday(LocalDate.parse("1990-01-01"));
        user.setGender(1);
        user.setStatus(1);
        user.setCreateTime(LocalDateTime.now());
        user.setLastUpdateTime(LocalDateTime.now());
        user.setDeleteFlag(1);

        System.out.println("user1:" + user);
        int result = userMapper.insert(user);
        System.out.println("result:" + result);
        System.out.println("user2:" + user);

        userMapper.selectList(null);
    }

    @Test
    void testUpdateOfCondition1(){
        User user = new User();
        user.setId(1);
        user.setUsername("admin222");
        user.setName("管理员222");
        user.setBirthday(LocalDate.parse("1990-12-31"));
        user.setGender(1);
        user.setStatus(0);
        user.setLastUpdateTime(LocalDateTime.now());
        user.setDeleteFlag(1);

        UpdateWrapper updateWrapper = new UpdateWrapper<User>();
        updateWrapper.set("username", user.getUsername());
        userMapper.update(user, updateWrapper);
    }

    @Test
    void testSelectOfCondition1(){
        // 创建 QueryWrapper 对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 模糊查询
        // queryWrapper.like("username", "adm");
        // 出生日期小于等于
        queryWrapper.lt("birthday", "1990-01-01");
        // 查询字段
        queryWrapper.select( "username", "name", "birthday", "gender", "status","delete_flag");
        // 查询
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println("-------------------------------------------");
        users.forEach(System.out::println);
    }

    @Test
    void testSelectOfCondition2(){
        // 创建 LambdaQueryWrapper 对象
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                // 模糊查询username
                // .like(User::getUsername, "adm")
                // 出生日期小于等于
                .lt(User::getBirthday, "1990-01-01")
                // 查询字段
                .select(User::getName, User::getBirthday);
        // 查询
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println("-------------------------------------------");
        users.forEach(System.out::println);
    }

    @Test
    void testSelectByQueryWrapper1(){
        /*
            select
                id, name, balance, birthday, gender
            from user
            where delete_flag = 0
              and status = 1
              and balance > 4000
              and username like '%wu%';
        *  */
        // 构建查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("delete_flag", 0)
                .eq("status", 1)
                .ge("balance", 4000)
                .like("username", "wu");
        // 设置回显的字段
        queryWrapper.select("id", "name", "balance", "birthday", "gender");
        // 执行查询
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println("-------------------------------------------");
        users.forEach(System.out::println);
    }

    @Test
    void testSelectByLambdaQueryWrapper1(){
        /*
            select
                id, name, balance, birthday, gender
            from user
            where delete_flag = 0
              and status = 1
              and balance > 4000
              and username like '%wu%';
        *  */
        // 构建查询条件
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(User::getDeleteFlag, 0)
                .eq(User::getStatus, 1)
                .ge(User::getBalance, 4000)
                .like(User::getUsername, "wu");
        // 设置回显的字段
        queryWrapper.select(User::getId, User::getName, User::getBalance, User::getBirthday, User::getGender);
        // 执行查询
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println("-------------------------------------------");
        users.forEach(System.out::println);
    }
    @Test
    void testUpdateByQueryWrapper1(){
        /*
            update user
            set balance = 2000
            where username = 'lisi';
        * */
        User user = new User();
        user.setBalance(2000.0);

        // 构建查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", "lisi");
        // 执行更新
        userMapper.update(user, queryWrapper);
    }
    @Test
    void testUpdateByUpdateWrapper1(){
        /*
            update user
            set balance = 2000
            where username = 'lisi';
        * */
        User user = new User();
        user.setUsername("lisi");
        user.setBalance(2000.0);

        // 构建查询条件
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                // 设置更新内容
                .set("balance", user.getBalance())
                // 设置更新条件
                .eq("username", user.getUsername());

        // 使用UpdateWrapper第一个参数可以为null
        userMapper.update(null, updateWrapper);
    }

    @Test
    void testUpdateByUpdateWrapper2(){
        /*
            update user
            set balance = balance - 200
            where id in (1,2);
        * */
        List<Integer> ids = List.of(1, 2);

        // 构建查询条件
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        // 设置更新内容
        updateWrapper.setSql("balance = balance - 200");
        // 设置更新条件
        updateWrapper.in("id", ids);

        // 使用UpdateWrapper第一个参数可以为null
        userMapper.update(null, updateWrapper);
    }

    @Test
    void testUpdateByLambdaUpdateWrapper1(){
        /*
            update user
            set balance = balance - 200
            where id in (1,2);
        * */
        List<Integer> ids = List.of(1, 2);

        // 构建查询条件
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        // 设置更新条件
        wrapper.in(User::getId, ids);

        int amount = 200;
        // 将wrapper传递到mapper层
        userMapper.updateBalanceByIds(wrapper, amount);
    }
}
