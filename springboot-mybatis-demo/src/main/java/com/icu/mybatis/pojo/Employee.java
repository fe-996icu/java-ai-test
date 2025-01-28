package com.icu.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Byte gender;
    private String phone;
    private LocalDate birthday;
    private Integer departmentId;
    private Integer jobId;
    private LocalDate joinDate;
    private LocalDateTime updateDate;
    private Byte status;
}
