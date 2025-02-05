package com.icu.mybatisplus.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.icu.mybatisplus.enums.UserStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户信息")
public class UserVO {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("username")
    private String username;
    @ApiModelProperty("name")
    private String name;
    @ApiModelProperty("balance")
    private Double balance;
    @ApiModelProperty("birthday")
    private LocalDate birthday;
    @ApiModelProperty("gender")
    private Integer gender;
    @ApiModelProperty("status")
    // 使用枚举类型，避免返回给前端的时候显示数字，而是显示对应的枚举值
    private UserStatus status;
    @ApiModelProperty("createTime")
    private LocalDateTime createTime;
    @ApiModelProperty("lastUpdateTime")
    private LocalDateTime lastUpdateTime;
    @ApiModelProperty("deleteFlag")
    private Integer deleteFlag;

    private List<AddressVO> addressList;
}
