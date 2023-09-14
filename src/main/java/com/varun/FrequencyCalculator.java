package com.varun;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyCalculator {

    /**
     * @param data String format of input data
     * @return HashMap with key as character & long as the frequency of character
     */
    public static Map<Character, Long> calculateFrequency(String data) {
        return data.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));
    }
}
