package com.icu.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Double balance;
    private LocalDate birthday;
    private Integer gender;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateTime;
    @TableLogic(value = "0",delval = "1") // 标记逻辑删除字段
    private Integer deleteFlag;
}
