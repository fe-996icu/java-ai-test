package com.icu.mybatis.services;

import com.icu.mybatis.pojo.Clazz;
import com.icu.mybatis.vo.clazz.ClazzPageVo;

import java.util.List;

public interface ClazzService {
    void addClazz(Clazz clazz);
    void deleteClazz(Integer id);
    void updateClazz(Clazz clazz);
    List<Clazz> findAll();
    List<Clazz> page(ClazzPageVo param);
}
