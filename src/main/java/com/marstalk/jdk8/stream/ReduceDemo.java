package com.marstalk.jdk8.stream;

import java.util.Arrays;
import java.util.List;

public class ReduceDemo {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result = numbers
                .stream()
                .reduce(0, (subtotal, element) -> subtotal + element);
        System.out.println(result);//21

       numbers.stream().map(String::valueOf).reduce("", (s, b) -> s + b);
    }

}
