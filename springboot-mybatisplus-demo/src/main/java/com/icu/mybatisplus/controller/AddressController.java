package com.icu.mybatisplus.controller;

import com.icu.mybatisplus.common.Result;
import com.icu.mybatisplus.pojo.Address;
import com.icu.mybatisplus.service.AddressService;
import com.icu.mybatisplus.vo.AddressVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    public Result<List<Address>> getAddressByUserId(@RequestParam Integer userId) {
        List<Address> list = addressService.findAddressByUserId(userId);
        log.info("查询用户id为[{}]的地址列表：{}", userId, list);
        return Result.ok(list);
    }
}
