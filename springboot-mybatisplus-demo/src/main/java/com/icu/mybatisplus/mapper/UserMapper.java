package com.icu.mybatisplus.mapper;


import com.icu.mybatisplus.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper{
    @Select("select * from user where id = #{id}")
    User selectById(Integer id);
}
