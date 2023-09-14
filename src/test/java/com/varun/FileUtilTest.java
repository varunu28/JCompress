package com.varun;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileUtilTest {

    @Test
    public void readContentForFileExist(@TempDir Path tempDir) throws IOException {
        // Arrange
        Path path = tempDir.resolve("sample.txt");
        String data = "sample data \n other sample data";
        Files.write(path, data.getBytes());

        // Act
        String result = FileUtil.readContent(path.toString());

        // Assert
        assertEquals(data, result);
    }

    @Test
    public void readContentFileNotFoundException() {
        assertThrows(FileNotFoundException.class, () -> FileUtil.readContent("unavailable_file.txt"));
    }
}