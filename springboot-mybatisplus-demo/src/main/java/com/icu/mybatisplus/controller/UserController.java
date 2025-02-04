package com.icu.mybatisplus.controller;

import cn.hutool.core.bean.BeanUtil;
import com.icu.mybatisplus.common.Result;
import com.icu.mybatisplus.dto.UserFormDTO;
import com.icu.mybatisplus.pojo.User;
import com.icu.mybatisplus.query.UserListQuery;
import com.icu.mybatisplus.service.UserService;
import com.icu.mybatisplus.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "保存用户信息")
    @PostMapping()
    Result<Integer> saveUser(@RequestBody UserFormDTO userDto){
        log.info("保存用户信息：{}", userDto);

        User user = BeanUtil.copyProperties(userDto, User.class);
        userService.save(user);

        log.info("保存用户信息成功：{}", user);

        return Result.ok(user.getId());
    }

    @ApiOperation(value = "删除用户信息")
    @DeleteMapping("/{id}")
    Result<Boolean> deleteUser(@PathVariable Integer id){
        log.info("删除用户信息：{}", id);

        boolean result = userService.removeById(id);

        log.info("删除用户信息[{}]：{}", id, result);

        return Result.ok(result);
    }

    @ApiOperation(value = "修改用户信息")
    @PutMapping()
    Result<Boolean> updateUser(@RequestBody UserFormDTO userDto){
        log.info("修改用户信息：{}", userDto);

        User user = BeanUtil.copyProperties(userDto, User.class);
        user.setLastUpdateTime(LocalDateTime.now());
        boolean result = userService.updateById(user);

        log.info("修改用户信息[{}]：{}", userDto.getId(), result);
        return Result.ok(result);
    }

    @ApiOperation(value = "查询单个用户信息")
    @GetMapping("/{id}")
    Result<UserVO> getUser(@PathVariable Integer id){
        log.info("查询用户信息：{}", id);

        User user = userService.getById(id);
        UserVO userVo = BeanUtil.copyProperties(user, UserVO.class);

        log.info("查询用户信息：{}", user);

        return Result.ok(userVo);
    }

    @ApiOperation(value = "查询所有用户信息")
    @GetMapping()
    Result<List<UserVO>> findAllList(){
        List<User> list = userService.list();

        List<UserVO> result = BeanUtil.copyToList(list, UserVO.class);

        return Result.ok(result);
    }


    @ApiOperation(value = "修改用户余额")
    @PatchMapping("/{id}")
    Result<Boolean> updateUserBalance(@PathVariable Integer id, @RequestParam Double amount){
        log.info("修改用户余额：{}-{}", id, amount);

        User user = new User();
        user.setId(id);
        user.setBalance(amount);
        boolean result = userService.updateById(user);
        log.info("修改用户余额[{}]：{}", id, result);

        return Result.ok(result);
    }

    @ApiOperation(value = "扣除用户余额")
    @PatchMapping("/{id}/deduct/{money}")
    Result<Boolean> updateUserBalanceSub(@PathVariable Double money, @PathVariable Integer id){
        log.info("修改用户余额：[{}]-{}", id, money);

        Boolean result = userService.deductBalance(id, money);

        log.info("修改用户余额[{}]：{}", id, result);

        return Result.ok(result);
    }

    // 复杂查询
    @GetMapping("/list")
    Result<List<UserVO>> findList(UserListQuery userQuery){
        List<User> list = userService.findList(userQuery);
        return Result.ok(BeanUtil.copyToList(list, UserVO.class));
    }
}
