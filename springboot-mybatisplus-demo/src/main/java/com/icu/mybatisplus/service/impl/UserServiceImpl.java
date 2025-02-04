package com.icu.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icu.mybatisplus.mapper.UserMapper;
import com.icu.mybatisplus.pojo.User;
import com.icu.mybatisplus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
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
        this.baseMapper.deductBalanceById(id, money);

        return true;
    }
}
