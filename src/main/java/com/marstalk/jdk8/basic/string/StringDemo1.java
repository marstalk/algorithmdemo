package com.marstalk.jdk8.basic.string;

/**
 * Created by mars on 6/6/18.
 */
public class StringDemo1 {
    public static void main(String[] args) {
        int count = 1000000000;

        long begin = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            String a = "welcome";
        }
        long end =  System.currentTimeMillis();
        System.out.println(end - begin);


        begin = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            String a = new String("welcome");
        }
        end =  System.currentTimeMillis();
        System.out.println(end - begin);

    }

}
