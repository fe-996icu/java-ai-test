package com.icu.mybatisplus.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(staticName = "of")
public class UserInfo {
    private Integer age;
    private String intro;
}
