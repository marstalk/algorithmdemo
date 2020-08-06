package com.marstalk.jdk8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FindFirst {

    public static void main(String[] args) {

        List<String> strings = Arrays.asList("A", "B", "C", "D", "s", "BBC", "Z", "AS");
//
//        String s = strings.stream().filter(str -> str.equalsIgnoreCase("A")).findFirst().orElse("N");
//        System.out.println(s);
//
//        List<String> list = strings.stream().filter(str -> str.equalsIgnoreCase("A")).collect(Collectors.toList());
//        System.out.println(list);
//
//        Map<String, List<String>> listMap = strings.stream().collect(Collectors.groupingBy(String::toString));
//        System.out.println(listMap);

        List<String> collect = strings.stream().peek(System.out::print)
                .filter(s1 -> s1.length() < 3).peek(System.out::print)
                .sorted(Comparator.comparing(String::valueOf).reversed()).peek(System.out::print)
                .filter(s1 -> s1.length() < 2).peek(System.out::print)
                .collect(Collectors.toList());
        System.out.println(collect.toString());

    }
}
