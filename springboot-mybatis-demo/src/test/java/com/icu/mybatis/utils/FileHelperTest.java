package com.icu.mybatis.utils;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileHelperTest {

    @Test
    public void getUniqueFileName_WithNormalFileName_ShouldReturnUniqueFileName() {
        String fileName = "example.txt";
        String uniqueFileName = FileHelper.getUniqueFileName(fileName);
        Assertions.assertTrue(uniqueFileName.contains(".txt"));
        Assertions.assertNotEquals(fileName, uniqueFileName);
    }

    @Test
    public void getUniqueFileName_WithoutExtension_ShouldReturnUniqueFileNameWithEmptyExtension() {
        String fileName = "example";
        String uniqueFileName = FileHelper.getUniqueFileName(fileName);
        Assertions.assertTrue(uniqueFileName.contains("."));
        Assertions.assertNotEquals(fileName, uniqueFileName);
    }

    @Test
    public void getUniqueFileName_WithMultipleDots_ShouldReturnUniqueFileNameWithLastExtension() {
        String fileName = "example.tar.gz";
        String uniqueFileName = FileHelper.getUniqueFileName(fileName);
        Assertions.assertTrue(uniqueFileName.contains(".gz"));
        Assertions.assertNotEquals(fileName, uniqueFileName);
    }

    @Test
    public void getUniqueFileName_EmptyFileName_ShouldReturnUniqueFileNameWithEmptyExtension() {
        String fileName = "";
        String uniqueFileName = FileHelper.getUniqueFileName(fileName);
        Assertions.assertTrue(uniqueFileName.contains("."));
        Assertions.assertNotEquals(fileName, uniqueFileName);
    }

    @Test
    public void getUniqueFileName_OnlyExtension_ShouldReturnUniqueFileNameWithExtension() {
        String fileName = ".txt";
        String uniqueFileName = FileHelper.getUniqueFileName(fileName);
        Assertions.assertTrue(uniqueFileName.contains(".txt"));
        Assertions.assertNotEquals(fileName, uniqueFileName);
    }
}
