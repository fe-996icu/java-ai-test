package com.icu.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.ASSIGN_ID)
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
    private Integer deleteFlag;
}
