package com.icu.mybatisplus.mapper;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.icu.mybatisplus.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // wrapper必须用@Param注解修饰，否则xml中无法识别，参数名必须为 Constants.WRAPPER 或者 @Param("ew")
    void updateBalanceByIds(@Param(Constants.WRAPPER) LambdaUpdateWrapper<User> wrapper, @Param("amount") int amount);
}
