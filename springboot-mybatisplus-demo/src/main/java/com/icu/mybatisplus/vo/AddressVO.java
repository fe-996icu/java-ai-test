package com.icu.mybatisplus.vo;

import lombok.Data;

@Data
public class AddressVO {
    private Integer id;
    private Integer userId;
    private String province;
    private String city;
    private String county;
    private String street;
    private String mobile;
    private String contact;
    private String isDefault;
    private String notes;
}
