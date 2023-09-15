package com.varun;

import com.varun.tree.HuffTree;
import com.varun.tree.TreeBuilder;

import java.io.IOException;
import java.util.Map;

public class CompressRunner {

    public static void main(String[] args) throws IOException {
        String inputFilePath = "data.txt";
        String encodedOutputFilePath = "encoded_output.txt";
        String decodedOutputFilePath = "decoded_output.txt";
        String headerFilePath = "header.txt";

        // Encode
        String data = FileUtil.readContent(inputFilePath);
        Map<Character, Long> frequencyMap = FrequencyCalculator.calculateFrequency(data);
        HuffTree root = TreeBuilder.buildTree(frequencyMap);
        String encoded = Translator.encode(data, root);

        // Write to output
        FileUtil.writeContent(encoded, encodedOutputFilePath);
        FileUtil.serializeHashMap(frequencyMap, headerFilePath);

        // Decode
        String encodedData = FileUtil.readContent(encodedOutputFilePath);
        Map<Character, Long> characterLongHashMap = FileUtil.deserializeHashMap(headerFilePath);
        HuffTree encodedRoot = TreeBuilder.buildTree(characterLongHashMap);
        String decodedData = Translator.decode(encodedData, encodedRoot);

        // Verify
        System.out.println(decodedData.equals(data));

        // Write to output file
        FileUtil.writeContent(decodedData, decodedOutputFilePath);
    }
}
