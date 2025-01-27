package com.icu.mybatis.mapper;

import com.icu.mybatis.pojo.Edu;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface EduMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into edu (name) values (#{name})")
    void addEdu(Edu edu);

    @Select("select id, name, last_update_time, create_time from edu")
    List<Edu> findAll();

    @Delete("delete from edu where id = #{id}")
    void deleteEdu(Integer id);

    @Update("update edu set name = #{name}, last_update_time = #{lastUpdateTime} where id = #{id}")
    void updateEdu(Edu edu);
}
