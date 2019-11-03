package com.marstalk.jdk8.process;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Mars
 * Created on 2018/8/27
 */
public class ProcessDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("A", "B", "C", "D", "s", "BBC", "Z", "AS");
        strings.stream()
                .peek(System.out::print)
                .filter(s -> s.length() < 3)
                .peek(System.out::print)
                .sorted(Comparator.comparing(String::valueOf).reversed())
                .peek(System.out::print)
                .filter(s -> s.length() < 2)
                .peek(System.out::print)
                .forEach(System.out::print);
    }
}
