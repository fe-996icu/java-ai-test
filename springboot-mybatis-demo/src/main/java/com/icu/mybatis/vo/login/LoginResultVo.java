package com.icu.mybatis.vo.login;

import lombok.Data;

@Data
public class LoginResultVo {
    private Integer id;
    private String username;
    private String name;
    private String token;
}
