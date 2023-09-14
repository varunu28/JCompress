package com.varun;

import com.varun.tree.HuffTree;
import com.varun.tree.TreeBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslatorTest {

    private static final String INPUT_DATA = "AAABBBBBCCCCCCCCCDDDDDEEFG";

    private static HuffTree root;

    @BeforeAll
    static void setUp() {
        root = TreeBuilder.buildTree(FrequencyCalculator.calculateFrequency(INPUT_DATA));
    }

    @Test
    public void correctTranslationSuccess() {
        String encodedData = Translator.encode(INPUT_DATA, root);
        String decodedData = Translator.decode(encodedData, root);

        assertEquals(INPUT_DATA, decodedData);
    }
}