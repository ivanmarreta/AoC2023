package dev.ivanmarreta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class Day1 {

    private static final String DIGITS_REGEX = "\\D+";

    private static final Map<String, String> digitsMap = Map.of("one", "o1e",
            "two", "t2o", "three", "t3e", "four", "f4r", "five", "f5e",
            "six", "s6x", "seven", "s7n", "eight", "e8t", "nine", "n9e");

    public static void main(String[] args) throws IOException {

        String fileName = "src/main/resources/day1/input1.txt";
        long total = 0;

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            for (String line : stream.toList()) {
                line = transformNumbers(line);
                String[] digitsArray = line.split(DIGITS_REGEX);

                total += getResult(digitsArray);
            }
        }

        System.out.println(total);
    }

    private static String transformNumbers(String line) {
        int position = 999;
        String number = "";
        for (Map.Entry<String, String> entry : digitsMap.entrySet()) {
            String key = entry.getKey();

            int numberPosition = line.indexOf(key);
            if (numberPosition != -1 && numberPosition < position) {
                position = numberPosition;
                number = key;
            }
        }

        if (number.isEmpty()) {
            return line;
        }

        line = line.replaceFirst(number, digitsMap.get(number));
        return transformNumbers(line);
    }

    private static Long getResult(String[] digitsArray) {
        List<String> digits = Arrays.stream(digitsArray).filter(s -> !s.isEmpty()).toList();
        String first = digits.get(0);
        if (first.length() > 1) {
            first = first.substring(0, 1);
        }

        String last = digits.get(digits.size()-1);
        if (last.length() > 1) {
            last = last.substring(last.length()-1);
        }

        return Long.parseLong(first + last);
    }

}
