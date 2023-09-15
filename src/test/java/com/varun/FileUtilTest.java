package com.varun;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void writeContentToFileSuccess() throws IOException {
        // Arrange
        String outputPath = "sample.txt";
        String data = "sample data";

        // Act
        FileUtil.writeContent(data, outputPath);

        // Assert
        Path path = Path.of(outputPath);
        assertTrue(path.toFile().exists());

        // cleanup
        assertTrue(path.toFile().delete());
    }

    @Test
    public void writeContentToExistingFileException(@TempDir Path tempDir) throws IOException {
        // Arrange
        Path path = tempDir.resolve("sample.txt");
        String data = "sample data";
        Files.write(path, data.getBytes());

        // Act
        assertThrows(FileAlreadyExistsException.class,
                () -> FileUtil.writeContent("sample data", path.toString()));
    }
}