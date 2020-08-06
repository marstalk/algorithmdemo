package com.marstalk.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo1 {

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("a1", "a2", "b1", "c2", "c1", "a0");

        List<String> result = stringList.stream().filter(str -> str.startsWith("c")).map(String::toUpperCase).sorted().collect(Collectors.toList());
        System.out.println(result.toString());

    }
}
