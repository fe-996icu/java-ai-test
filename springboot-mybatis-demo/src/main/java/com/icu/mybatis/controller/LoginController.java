package com.icu.mybatis.controller;

import com.icu.mybatis.common.Result;
import com.icu.mybatis.common.ResultCodeEnum;
import com.icu.mybatis.services.LoginService;
import com.icu.mybatis.utils.TokenHelper;
import com.icu.mybatis.vo.login.LoginParamVo;
import com.icu.mybatis.vo.login.LoginResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private TokenHelper tokenHelper;
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result<LoginResultVo> login(@RequestBody LoginParamVo param){
        log.info("登录接口，请求参数：{}", param);
        LoginResultVo result = loginService.login(param);

        if(result == null){
            log.info("登录失败：{}", param);
            return Result.build(null, ResultCodeEnum.LOGIN_FAIL);
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", result.getUsername());
        claims.put("name", result.getName());
        String token = tokenHelper.generateToken(claims);
        result.setToken(token);
        log.info("登录成功：{}", result);
        return Result.ok(result);
    }
}
