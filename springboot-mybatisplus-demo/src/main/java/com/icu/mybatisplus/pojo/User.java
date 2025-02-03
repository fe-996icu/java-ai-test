package com.icu.mybatisplus.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private LocalDate birthday;
    private Integer gender;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateTime;
    private Integer delete_flag;
}
