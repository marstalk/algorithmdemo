package com.marstalk.jdk8.jvm.chapter8.p3.demo5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InvokeDynamicDemo {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        objects.stream()
                .filter(o -> o.getClass().equals("a"))
                .collect(Collectors.toList());
    }
}
