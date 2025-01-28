package com.icu.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String no;
    private Integer clazzId;
    private Byte gender;
    private String phone;
    private Integer eduId;
    private LocalDateTime lastUpdateTime;
    private LocalDateTime createDate;
}
