package com.icu.mybatis.services;

import com.icu.mybatis.pojo.Edu;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EduService {
    void addEdu(Edu edu);
    List<Edu> findAll();
    void deleteEdu(Integer id);
    void updateEdu(Edu edu);
}
