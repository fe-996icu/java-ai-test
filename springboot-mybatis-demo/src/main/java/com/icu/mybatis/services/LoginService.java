package com.icu.mybatis.services;

import com.icu.mybatis.vo.login.LoginParamVo;
import com.icu.mybatis.vo.login.LoginResultVo;

public interface LoginService {

    LoginResultVo login(LoginParamVo param);
}
