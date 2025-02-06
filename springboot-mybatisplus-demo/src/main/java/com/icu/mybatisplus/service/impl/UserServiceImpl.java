package com.icu.mybatisplus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.icu.mybatisplus.enums.UserStatus;
import com.icu.mybatisplus.mapper.UserMapper;
import com.icu.mybatisplus.pojo.Address;
import com.icu.mybatisplus.pojo.User;
import com.icu.mybatisplus.query.UserListQuery;
import com.icu.mybatisplus.service.UserService;
import com.icu.mybatisplus.vo.AddressVO;
import com.icu.mybatisplus.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    @Transactional // 开启事务
    public Boolean deductBalance(Integer id, Double money) {
        // 查询用户信息
        User user = this.getById(id);

        // 验证用户是否存在，用户状态是否正常
        if (user == null || user.getStatus() != UserStatus.NORMAL) {
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

    @Override
    public UserVO getUserAndAddress(Integer userId) {
        // 查询用户信息
        User user = this.getById(userId);

        // 验证用户是否存在，用户状态是否正常
        if (user == null || user.getStatus() != UserStatus.NORMAL) {
            log.error("用户不存在或状态不正常");
            return null;
        }

        // 查询用户地址列表，使用Db工具类，如果不使用Db工具类，就需要在这个Service注入AddressService，而
        List<Address> list = Db.lambdaQuery(Address.class).eq(Address::getUserId, userId).list();

        // 合并到UserVO中
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        userVO.setAddressList(BeanUtil.copyToList(list, AddressVO.class));

        return userVO;
    }

    @Override
    public List<UserVO> getUserAndAddressByUserIds(List<Integer> userIds) {
        List<User> list = this.lambdaQuery()
                .in(User::getId, userIds)
                .eq(User::getStatus, 1) // 直接在 SQL 里过滤，避免 Java 端再筛选
                .list();

        if (list.isEmpty()) {
            // 明示这个方法的返回值是一个空列表，让阅读代码的人一眼就能看出
            return Collections.emptyList();
        }

        // 有效的 userIds
        List<Integer> validUserIds = list.stream().map(User::getId).toList(); // 获取查询回来的用户id，并转换成List集合
        // 获取所有有效用户的地址信息
        Map<Integer, List<Address>> map = Db.lambdaQuery(Address.class)
                .in(Address::getUserId, validUserIds) // 获取所有有效用户的地址信息
                .list() // 查询，返回List集合
                .stream().collect(Collectors.groupingBy(Address::getUserId)); // 按照userId将地址信息分组

        List<UserVO> userVOS = BeanUtil.copyToList(list, UserVO.class);

        // 循环遍历userVOS，将地址信息赋值给userVO
        for (UserVO userVO : userVOS) {
            List<Address> addressList = map.get(userVO.getId());
            userVO.setAddressList(BeanUtil.copyToList(addressList, AddressVO.class));
        }

        // 下面这种方式是N+1查询，性能差（即 1 次查询用户，N 次查询地址）
        // for (UserVO userVO : userVOS) {
        //     List<Address> list1 = Db.lambdaQuery(Address.class).eq(Address::getUserId, userVO.getId()).list();
        //     userVO.setAddressList(BeanUtil.copyToList(list1, AddressVO.class));
        // }

        return userVOS;
    }

    @Override
    public Page<User> findListPage(UserListQuery query) {
        // 组合分页查询条件
        Page<User> page = new Page<>(query.getPageNum(), query.getPageSize());

        // 设置排序条件
        List<OrderItem> orderItems = new ArrayList<>();
        if(query.getOrderBy() != null){
            orderItems.add(new OrderItem().setColumn(query.getOrderBy()).setAsc(query.getIsAsc()));
        }else{
            orderItems.add(new OrderItem().setColumn("last_update_time").setAsc(false));
        }
        page.setOrders(orderItems);

        // 查询
        this.lambdaQuery()
                .like(query.getUsername() != null, User::getUsername, query.getUsername())
                .eq(query.getStatus() != null, User::getStatus, query.getStatus())
                .page(page);

        return page;
    }
}
