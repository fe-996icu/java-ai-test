package com.icu.mybatis.controller;

import com.github.pagehelper.Page;
import com.icu.mybatis.common.Result;
import com.icu.mybatis.pojo.Clazz;
import com.icu.mybatis.services.ClazzService;
import com.icu.mybatis.vo.clazz.ClazzPageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/clazz")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    /**
     * 添加班级信息
     * @param clazz
     * @return
     */
    @PostMapping
    public Result<Integer> addClazz(@RequestBody Clazz clazz){
        log.info("新增班级接口，请求参数：{}", clazz);
        clazzService.addClazz(clazz);
        log.info("新增班级接口，返回结果：{}", clazz.getId());
        return Result.ok(clazz.getId());
    }

    @DeleteMapping("/{id}")
    public Result deleteClazz(@PathVariable(required = true) Integer id) {
        log.info("删除班级接口：{}", id);
        clazzService.deleteClazz(id);
        log.info("删除班级成功");
        return Result.ok(null);
    }

    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz) {
        log.info("修改班级接口-参数：{}", clazz);
        clazzService.updateClazz(clazz);
        log.info("修改班级成功-：{}", clazz.getId());
        return Result.ok(null);
    }

    /**
     * 查询所有班级信息
     * @return
     */
    @GetMapping
    public Result<List<Clazz>> findAll(){
        log.info("查询所有班级接口");
        List<Clazz> list = clazzService.findAll();
        log.info("查询所有班级接口，返回结果：{}", list);
        return Result.ok(list);
    }

    /**
     * 分页查询班级信息
     * @return
     */
    @GetMapping("/page")
    public Result<Map<String, Object>> page(ClazzPageVo param){
        log.info("分页查询班级接口：{}", param);
        List<Clazz> list = clazzService.page(param);
        Page<Clazz> page = (Page<Clazz>) list;
        log.info("分页查询班级接口，返回结果：{}", list);
        Map<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("rows", page);
        return Result.ok(map);
    }
}
