package com.icu.mybatisplus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.icu.mybatisplus.pojo.User;
import com.icu.mybatisplus.query.UserListQuery;
import com.icu.mybatisplus.vo.UserVO;

import java.util.List;

public interface UserService extends IService<User> {
    Boolean deductBalance(Integer id, Double money);

    List<User> findList(UserListQuery userQuery);

    UserVO getUserAndAddress(Integer userId);

    List<UserVO> getUserAndAddressByUserIds(List<Integer> userIds);

    Page<User> findListPage(UserListQuery userQuery);
}
