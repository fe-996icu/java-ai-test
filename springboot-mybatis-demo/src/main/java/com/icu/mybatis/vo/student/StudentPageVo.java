package com.icu.mybatis.vo.student;


import lombok.Data;

@Data
public class StudentPageVo {
    private Integer page = 1;
    private Integer pageSize = 2;
    private String name;
    private String no;
    private Integer clazzId;
    private Byte gender;
    private String phone;
    private Integer eduId;
}
