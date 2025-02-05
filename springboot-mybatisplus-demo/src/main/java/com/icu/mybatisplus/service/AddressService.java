package com.icu.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.icu.mybatisplus.pojo.Address;

import java.util.List;

public interface AddressService extends IService<Address> {
    List<Address> findAddressByUserId(Integer userId);
}
