package com.icu.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.icu.mybatisplus.pojo.User;

public interface UserService extends IService<User> {
    Boolean deductBalance(Integer id, Double money);
}
