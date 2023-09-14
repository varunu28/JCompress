package com.varun;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    /**
     * @param filePath String representing the path of file which needs to be read
     * @return String representing all the contents of the given file
     * @throws FileNotFoundException if there doesn't exist a file at given file path
     */
    public static String readContent(String filePath) throws FileNotFoundException {
        Path path = Paths.get(filePath);
        if (!path.toFile().exists()) {
            throw new FileNotFoundException(String.format("file with path: %s not found", filePath));
        }
        try {
            return Files.readString(path, Charset.defaultCharset());
        } catch (IOException e) {
            throw new FileReadException(e);
        }
    }
}

class FileReadException extends RuntimeException {

    public FileReadException(Throwable cause) {
        super(cause);
    }
}
