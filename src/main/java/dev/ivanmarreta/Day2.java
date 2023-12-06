package dev.ivanmarreta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

class Day2 {

    private static final String RED_COLOR   = "red";
    private static final String GREEN_COLOR = "green";
    private static final String BLUE_COLOR  = "blue";
    public static final int RED_CUBES   = 12;
    public static final int GREEN_CUBES = 13;
    public static final int BLUE_CUBES  = 14;

    public static void main(String[] args) throws IOException {

        String fileName = "src/main/resources/day2/input1.txt";

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            int gameSum = 0;
            int totalPowerOfMinSets = 0;

            for (String line : stream.toList()) {

                String[] gameBagsSplitArray = line.split(":");
                int gameNumber = Integer.parseInt(gameBagsSplitArray[0].replace("Game ", "").trim());

                String[] bagsSplitArray = gameBagsSplitArray[1].split(";");
                boolean impossible = false;

                int minRed = 0;
                int minGreen = 0;
                int minBlue = 0;

                for (String bag : bagsSplitArray) {
                    int counterRed = 0;
                    int counterGreen = 0;
                    int counterBlue = 0;

                    String[] cubesArray = bag.split(",");
                    for (String cube : cubesArray) {
                        String cubeString = cube.trim();
                        int cubeValue = Integer.parseInt(cubeString.substring(0, cubeString.indexOf(" ")));

                        if (cubeString.contains(RED_COLOR)) {
                            counterRed += cubeValue;
                        } else if (cubeString.contains(GREEN_COLOR)) {
                            counterGreen += cubeValue;
                        } else if (cubeString.contains(BLUE_COLOR)) {
                            counterBlue += cubeValue;
                        }

                        if (cubeString.contains(RED_COLOR) && cubeValue > minRed) {
                            minRed = cubeValue;
                        } else if (cubeString.contains(GREEN_COLOR) && cubeValue > minGreen) {
                            minGreen = cubeValue;
                        } else if (cubeString.contains(BLUE_COLOR) && cubeValue > minBlue) {
                            minBlue = cubeValue;
                        }

                        if (counterRed > RED_CUBES
                                || counterGreen > GREEN_CUBES
                                || counterBlue > BLUE_CUBES) {
                            impossible = true;
                        }
                    }
                }

                int powerOfMinimumSet = minRed * minGreen * minBlue;
                totalPowerOfMinSets += powerOfMinimumSet;

                if (!impossible) {
                    gameSum += gameNumber;
                }

            }

            System.out.println("Part 1: " + gameSum);
            System.out.println("Part 2: " + totalPowerOfMinSets);
        }
    }


}
