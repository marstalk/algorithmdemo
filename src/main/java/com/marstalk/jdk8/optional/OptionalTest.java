package com.marstalk.jdk8.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {


        String str = null;
        System.out.println(Optional.ofNullable(str).map(v -> str.length()).orElse(0));


        List<String> list = new ArrayList<>();
//        list = null;
        list.forEach(i -> System.out.println(i));

        list.add("welcome");
        list.add("to");
        list.add("my");
        list.add("life");
        list = null;
        Optional.ofNullable(list).orElse(new ArrayList<>()).forEach(i -> System.out.println(i));

    }
}
