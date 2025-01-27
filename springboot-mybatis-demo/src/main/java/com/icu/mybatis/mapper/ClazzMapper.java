package com.icu.mybatis.mapper;

import com.icu.mybatis.pojo.Clazz;
import com.icu.mybatis.pojo.Edu;
import com.icu.mybatis.vo.clazz.ClazzPageVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {

    void addClazz(Clazz clazz);

    void deleteClazz(Integer id);

    void updateClazz(Clazz clazz);

    List<Clazz> findAll();

    List<Clazz> page(ClazzPageVo param);

}