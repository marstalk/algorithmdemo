package com.marstalk.jdk8newfeature.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessFlowDemo {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("A", "B", "C", "D", "s", "BBC", "Z", "AS");
        List<String> collect = strings.stream()
                .peek(System.out::print)
                .filter(s1 -> s1.length() < 3)
                .peek(System.out::print)
                .sorted(Comparator.comparing(String::valueOf).reversed())
                .peek(System.out::print)
                .filter(s1 -> s1.length() < 2)
                .peek(System.out::print)
                .collect(Collectors.toList());
        System.out.println(collect.toString());
    }
}
