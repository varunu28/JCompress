package com.varun;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FrequencyCalculatorTest {

    @Test
    public void calculateFrequencySuccess() {
        // Arrange
        String data = "aabbbcc";

        // Act
        Map<Character, Long> characterLongMap = FrequencyCalculator.calculateFrequency(data);

        // Assert
        Map<Character, Long> expected = Map.of('a', 2L, 'b', 3L, 'c', 2L);
        assertTrue(Maps.difference(expected, characterLongMap).areEqual());
    }
}