package com.icu.mybatis.controller;

import com.icu.mybatis.annotation.RecordLog;
import com.icu.mybatis.common.Result;
import com.icu.mybatis.enums.OperateLogType;
import com.icu.mybatis.pojo.Edu;
import com.icu.mybatis.services.EduService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/edu")
public class EduController {
    @Autowired
    private EduService eduService;

    /**
     * 添加学历信息
     * @param edu
     * @return
     */
    @RecordLog(value = OperateLogType.ADD)
    @PostMapping
    public Result<Integer> addEdu(@RequestBody Edu edu){
        log.info("新增学历接口，请求参数：{}", edu);
        eduService.addEdu(edu);
        log.info("新增学历接口，返回结果：{}", edu.getId());
        return Result.ok(edu.getId());
    }

    @RecordLog(value = OperateLogType.DELETE)
    @DeleteMapping("/{id}")
    public Result deleteEdu(@PathVariable(required = true) Integer id) {
        log.info("删除学历接口：{}", id);
        eduService.deleteEdu(id);
        log.info("删除学历成功");
        return Result.ok(null);
    }

    @RecordLog(value = OperateLogType.UPDATE)
    @PutMapping
    public Result updateEdu(@RequestBody Edu edu) {
        log.info("修改学历接口-参数：{}", edu);
        eduService.updateEdu(edu);
        log.info("修改学历成功-：{}", edu.getId());
        return Result.ok(null);
    }

    /**
     * 查询所有学历信息
     * @return
     */
    @RecordLog(value = OperateLogType.SELECT)
    @GetMapping
    public Result<List<Edu>> findAll(){
        log.info("查询所有学历接口");
        List<Edu> list = eduService.findAll();
        log.info("查询所有学历接口，返回结果：{}", list);
        return Result.ok(list);
    }
}
