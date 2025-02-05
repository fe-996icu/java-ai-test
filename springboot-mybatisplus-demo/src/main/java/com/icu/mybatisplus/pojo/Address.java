package com.icu.mybatisplus.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
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
