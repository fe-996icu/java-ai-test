package com.icu.mybatis.mapper;

import com.icu.mybatis.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {
    void save(OperateLog operateLog);
}
