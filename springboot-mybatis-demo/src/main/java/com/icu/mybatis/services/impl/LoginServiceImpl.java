package com.icu.mybatis.services.impl;

import com.icu.mybatis.mapper.EmployeeMapper;
import com.icu.mybatis.mapper.LoginMapper;
import com.icu.mybatis.mapper.StudentMapper;
import com.icu.mybatis.services.LoginService;
import com.icu.mybatis.vo.login.LoginParamVo;
import com.icu.mybatis.vo.login.LoginResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    // @Autowired
    // private LoginMapper loginMapper;

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public LoginResultVo login(LoginParamVo param) {
        LoginResultVo result;

        result = employeeMapper.login(param);
        if(result != null){
            log.info("员工表查询到登录信息：{}", result);
            return result;
        }

        log.warn("员工表没查到登录信息：{}", param);
        result = studentMapper.login(param);
        if(result != null){
            log.info("学生表查询到登录信息：{}", result);
            return result;
        }
        log.warn("学生表没查到登录信息：{}", param);
        return result;
    }
}
