package com.icu.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.icu.mybatisplus.mapper.UserMapper;
import com.icu.mybatisplus.pojo.User;
import com.icu.mybatisplus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest
class SpringbootMybatisplusDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

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

    @Test
    void testLoopInsert(){
        long start = System.currentTimeMillis();

        int count = 100000;
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setUsername("user" + i);
            user.setName("name" + i);
            user.setBirthday(LocalDate.now());
            user.setGender(1);

            userService.save(user);
        }

        long end = System.currentTimeMillis();

        // 循环插入100000条数据，耗时：235386ms，耗时约4分钟
        log.info("循环插入{}条数据，耗时：{}ms", count, (end - start));
    }

    @Test
    void testBatchInsert(){
        long start = System.currentTimeMillis();

        List<User> users = new ArrayList<>();
        int count = 100000;
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setUsername("user" + i);
            user.setName("name" + i);
            user.setBirthday(LocalDate.now());
            user.setGender(1);
            users.add(user);

            // 每1000条执行一次批处理插入
            if (i % 1000 == 0) {
                userService.saveBatch(users);
                users.clear();
            }
        }

        // 最后一次不足1000条的，执行一次批处理插入
        if (!users.isEmpty()) {
            userService.saveBatch(users);
            users.clear();
        }

        long end = System.currentTimeMillis();

        // 批量插入100000条数据，耗时：18984ms，耗时约20秒
        // 开启rewriteBatchedStatements=true，耗时约6秒

        // rewriteBatchedStatements这个配置是mysql的配置，是jdbc驱动的配置，并不是mybatisplus的配置，从mysql3.1.1版本开始就有了这个配置，默认是关闭的
        // 开启这个配置后，再执行批处理，会自动将批处理语句合并为一条sql语句执行，从而提高性能
        // 如：insert into user (username, name, birthday, gender) values ('user0', 'name0', '2022-01-01', 1), ('user1', 'name1', '2022-01-01', 1), ('user2', 'name2', '2022-01-01', 1), ('user3', 'name3', '2022-01-01',)

        // 默认情况下，mysql的jdbc驱动是关闭这个配置的，所以需要手动开启
        // 不开启这个配置，执行批处理，会自动将批处理语句拆分成多条sql语句执行
        // 如：insert into user (username, name, birthday, gender) values ('user0', 'name0', '2022-01-01', 1)
        //     insert into user (username, name, birthday, gender) values ('user0', 'name0', '2022-01-01', 1)
        //     insert into user (username, name, birthday, gender) values ('user0', 'name0', '2022-01-01', 1)
        log.info("批量插入{}条数据，耗时：{}ms", count, (end - start));
    }
}
