package com.interview.Interview.OOPs.TuteDude.Streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
More exmaples: https://www.programiz.com/java-programming/lambda-expression
 */
public class Practise {

    public static void main(String[] args) {

        IntStream.range(5,20).forEach((x) -> System.out.println(x));

        System.out.println();

        //skips first 7 values
        IntStream.range(5,20).skip(7).forEach((x) -> System.out.println(x));

        System.out.println();

        Stream.of("banana", "apple","orange", "grape")
                .sorted()
                .findFirst()
                .ifPresent(x -> System.out.println(x));

        System.out.println();


        //streaming files. Streams can also be used on IO

        Arrays.stream(new int[] {1,2,3,4})
                .map((x) -> x*x*x)
                .average()
                .ifPresent(x -> System.out.println(x));

        System.out.println();


        try {
            Stream<String> lines = Files.lines(Paths.get("src/main/java/org/interview/prep/code/testing/Streams/line.txt"));
            lines.sorted()
                    .forEach((x) -> System.out.println(x));
            lines.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
