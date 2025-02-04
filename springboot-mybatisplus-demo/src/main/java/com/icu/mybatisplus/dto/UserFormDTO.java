package com.icu.mybatisplus.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel("用户表单实体")
public class UserFormDTO {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("username")
    private String username;
    @ApiModelProperty("password")
    private String password;
    @ApiModelProperty("name")
    private String name;
    @ApiModelProperty("balance")
    private Double balance;
    @ApiModelProperty("birthday")
    private LocalDate birthday;
    @ApiModelProperty("gender")
    private Integer gender;
    @ApiModelProperty("status")
    private Integer status;
}
