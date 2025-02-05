package com.icu.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.icu.mybatisplus.mapper.AddressMapper;
import com.icu.mybatisplus.pojo.Address;
import com.icu.mybatisplus.pojo.User;
import com.icu.mybatisplus.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
    @Override
    public List<Address> findAddressByUserId(Integer userId) {
        // 查询用户信息，如果不使用Db工具类，就需要在这个Service中注入UserService，而UserService中如果依赖了AddressService，那么就循环依赖了
        User user = Db.getById(userId, User.class);

        // 校验用户状态
        if (user == null || user.getStatus() != 1) {
            log.error("用户[{}]不存在或状态不正常", userId);
            return null;
        }

        // 查询用户地址
        List<Address> list = this.lambdaQuery().eq(Address::getUserId, userId).list();
        return list;
    }
}
