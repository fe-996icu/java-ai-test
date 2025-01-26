package com.icu.mybatis.controller;

import com.icu.mybatis.common.Result;
import com.icu.mybatis.utils.CommonHelper;
import com.icu.mybatis.utils.FileHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@RequestMapping("/file")
@RestController
public class FileController {
    @Value("${com.icu.fileStoreDir}")
    private String fileStoreDir;

    @PostMapping("/upload")
    public Result<String> uploadFile(
            // 前端文件参数名定义为 file
            @RequestParam("file")
            MultipartFile[] files
    ) throws IOException {
        for (MultipartFile file : files) {
            // 原始文件名
            log.info("上传文件：{}", file.getOriginalFilename());

            String fileName = FileHelper.getUniqueFileName(file.getOriginalFilename());

            // 创建文件对象
            File newFile = new File(this.fileStoreDir + fileName);
            // 保存文件到指定位置
            file.transferTo(newFile);
            log.info("文件保存路径：{}", newFile.getAbsoluteFile());
        }
        return Result.ok(null);
    }
}


