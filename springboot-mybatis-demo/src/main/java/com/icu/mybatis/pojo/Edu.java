package com.icu.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Edu {
    private Integer id;
    private String name;
    private LocalDateTime lastUpdateTime;
    private LocalDateTime createTime;
}
