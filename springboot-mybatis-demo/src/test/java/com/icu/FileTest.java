package com.icu;

import com.icu.mybatis.utils.FileHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FileTest {
    @Test
    public void testFileExt(){
        String fileName = "test.jpg";

        String extName = FileHelper.getExtName(fileName);

        Assertions.assertEquals("jpg", extName);
    }

    @Test
    public void testFileExtFail(){
        String fileName = "test.jpg";

        String extName = FileHelper.getExtName(fileName);

        Assertions.assertEquals("jpg", extName, "文件扩展名不匹配");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test.jpg", "test.png"})
    public void testParams(){
        System.out.println("testParams");
    }
}
