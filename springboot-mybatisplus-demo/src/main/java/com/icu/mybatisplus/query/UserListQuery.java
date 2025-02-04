package com.icu.mybatisplus.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserListQuery {
    private String username;
    private Double minBalance;
    private Double maxBalance;
    private Integer gender;
    private Integer status;
}
