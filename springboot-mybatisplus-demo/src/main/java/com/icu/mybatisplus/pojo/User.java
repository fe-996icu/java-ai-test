package com.icu.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.icu.mybatisplus.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user", autoResultMap = true) // 开启自动结果映射
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Double balance;
    private LocalDate birthday;
    private Integer gender;
    // 使用JacksonTypeHandler对json数据做转换
    @TableField(typeHandler = JacksonTypeHandler.class)
    private UserInfo info;
    // 使用枚举类型
    private UserStatus status;
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateTime;
    @TableLogic(value = "0",delval = "1") // 标记逻辑删除字段
    private Integer deleteFlag;
}
