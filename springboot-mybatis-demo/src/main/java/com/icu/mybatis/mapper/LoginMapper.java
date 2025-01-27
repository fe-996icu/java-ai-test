package com.icu.mybatis.mapper;

import com.icu.mybatis.vo.login.LoginParamVo;
import com.icu.mybatis.vo.login.LoginResultVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    LoginResultVo login(LoginParamVo param);
}
