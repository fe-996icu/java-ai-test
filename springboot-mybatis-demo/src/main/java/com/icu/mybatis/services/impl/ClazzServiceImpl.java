package com.icu.mybatis.services.impl;

import com.github.pagehelper.PageHelper;
import com.icu.mybatis.mapper.ClazzMapper;
import com.icu.mybatis.pojo.Clazz;
import com.icu.mybatis.services.ClazzService;
import com.icu.mybatis.vo.clazz.ClazzPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public void addClazz(Clazz clazz) {
        clazzMapper.addClazz(clazz);
    }

    @Override
    public void deleteClazz(Integer id) {
        clazzMapper.deleteClazz(id);
    }

    @Override
    public void updateClazz(Clazz clazz) {
        clazz.setLastUpdateTime(LocalDateTime.now());
        clazzMapper.updateClazz(clazz);
    }

    @Override
    public List<Clazz> page(ClazzPageVo param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        return clazzMapper.page(param);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }

}
