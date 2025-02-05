package com.icu.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icu.mybatisplus.mapper.UserMapper;
import com.icu.mybatisplus.pojo.User;
import com.icu.mybatisplus.query.UserListQuery;
import com.icu.mybatisplus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    @Transactional // 开启事务
    public Boolean deductBalance(Integer id, Double money) {
        // 查询用户信息
        User user = this.getById(id);

        // 验证用户是否存在，用户状态是否正常
        if (user == null || user.getStatus() != 1) {
            log.error("用户不存在或状态不正常");
            return false;
        }

        // 用户余额是否满足扣减金额
        if (user.getBalance() < money) {
            log.error("用户余额不足");
            return false;
        }

        // 执行扣减
        // this.baseMapper.deductBalanceById(id, money);

        double remainBalance = user.getBalance() - money;
        // update user set balance = balance - ? , status=? where id=? and balance=?
        this.lambdaUpdate()
                .eq(User::getId, id)
                .eq(User::getBalance, user.getBalance())    // 验证余额是否被修改，乐观锁，先比较再更新。
                // 余额大于0，则更新余额
                .set(remainBalance > 0, User::getBalance, remainBalance)
                // 余额为0，则将用户状态置为冻结
                .set(remainBalance == 0, User::getStatus, 2)
                .update();

        return true;
    }

    @Override
    public List<User> findList(UserListQuery userQuery) {
        /*
            select *
            from user
            where username like '%sun%'
              and status = 1
              and balance > 100
              and balance < 10000000;
        */

        // 使用lambdaQuery查询
        List<User> list = this.lambdaQuery()
                // 模糊查询，第一个参数为true，才会构建条件，和mybatis的动态sql中where、if等价
                .like(userQuery.getUsername()!=null, User::getUsername, userQuery.getUsername())
                // 等值查询
                .eq(userQuery.getStatus()!=null, User::getStatus, userQuery.getStatus())
                // 范围查询
                // .between(userQuery.getMinBalance()!=null && userQuery.getMaxBalance()!=null, User::getBalance, userQuery.getMinBalance(), userQuery.getMaxBalance())
                // 大于等于
                .gt(userQuery.getMinBalance()!=null, User::getBalance, userQuery.getMinBalance())
                // 小于等于
                .lt(userQuery.getMaxBalance()!=null, User::getBalance, userQuery.getMaxBalance())
                .list(); // 执行查询
        return list;
    }
}
