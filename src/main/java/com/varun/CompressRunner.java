package com.varun;

import com.varun.tree.HuffTree;
import com.varun.tree.TreeBuilder;

import java.io.FileNotFoundException;
import java.util.Map;

public class CompressRunner {

    public static void main(String[] args) throws FileNotFoundException {
        String data = FileUtil.readContent("data.txt");
        Map<Character, Long> frequencyMap = FrequencyCalculator.calculateFrequency(data);
        HuffTree root = TreeBuilder.buildTree(frequencyMap);
        String encoded = Translator.encode(data, root);
        System.out.println(encoded);
        System.out.println(Translator.decode(encoded, root).equals(data));
    }
}
