package dev.ivanmarreta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Day1 {

    private static final String DIGITS_REGEX = "\\D+";

    void main() throws IOException {

        String fileName = "src/main/resources/day1/input1.txt";
        long total = 0;

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            for (String line : stream.toList()) {
                String[] digitsArray = line.split(DIGITS_REGEX);

                total += getResult(digitsArray);
            }

        }

        System.out.println(total);
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
