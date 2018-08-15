package com.marstalk.jdk8newfeature.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindFirst {

    public static void main(String[] args) {

        List<String> strings = Arrays.asList("A", "B", "A", "A", "D");

        String s = strings.stream().filter(str -> str.equalsIgnoreCase("A")).findFirst().orElse("N");
        System.out.println(s);

        List<String> list = strings.stream().filter(str -> str.equalsIgnoreCase("A")).collect(Collectors.toList());
        System.out.println(list);

        Map<String, List<String>> listMap = strings.stream().collect(Collectors.groupingBy(String::toString));
        System.out.println(listMap);

    }
}
