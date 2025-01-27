package com.icu.mybatis.services.impl;

import com.icu.mybatis.mapper.EduMapper;
import com.icu.mybatis.pojo.Edu;
import com.icu.mybatis.services.EduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EduServiceImpl implements EduService {
    @Autowired
    private EduMapper eduMapper;

    @Override
    public void addEdu(Edu edu) {
        eduMapper.addEdu(edu);
    }

    @Override
    public List<Edu> findAll() {
        return eduMapper.findAll();
    }

    @Override
    public void deleteEdu(Integer id) {
        eduMapper.deleteEdu(id);
    }

    @Override
    public void updateEdu(Edu edu) {
        edu.setLastUpdateTime(LocalDateTime.now());
        eduMapper.updateEdu(edu);
    }
}
