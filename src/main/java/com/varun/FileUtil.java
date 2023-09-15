package com.varun;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * @param data           data to be written to the file
     * @param outputFilePath String representing the path of file which needs to be written to
     * @throws FileAlreadyExistsException if there already exists a file at given path
     */
    public static void writeContent(String data, String outputFilePath) throws FileAlreadyExistsException {
        Path path = Paths.get(outputFilePath);
        if (path.toFile().exists()) {
            throw new FileAlreadyExistsException(String.format("file with path: %s already exists", outputFilePath));
        }
        try {
            Files.write(path, data.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new FileWriteException(e);
        }
    }

    /**
     * @param hashMap  map to be serialized
     * @param filePath file where the serialized version needs to be written
     */
    public static void serializeHashMap(Map<Character, Long> hashMap, String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(hashMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param filePath path from where serialized version is read from
     * @return Deserialized hashmap
     */
    public static HashMap<Character, Long> deserializeHashMap(String filePath) {
        Map<Character, Long> hashMap = null;
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (HashMap<Character, Long>) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

class FileReadException extends RuntimeException {

    public FileReadException(Throwable cause) {
        super(cause);
    }
}

class FileWriteException extends RuntimeException {

    public FileWriteException(Throwable cause) {
        super(cause);
    }
}