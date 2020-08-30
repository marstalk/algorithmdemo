package com.marstalk.jdk8.jvm.chapter8.p3.demo4;

import java.io.Serializable;

public class OverloadPriority {
    public static void hi(Object arg){
        System.out.println("hi object");
    }
//    public static void hi(int arg){
//        System.out.println("hi int");
//    }
//    public static void hi(long arg){
//        System.out.println("hi long");
//    }
    public static void hi(Character arg){
        System.out.println("hi Character");
    }
//    public static void hi(char arg){
//        System.out.println("hi char");
//    }
    public static void hi(char... arg){
        System.out.println("hi char...");
    }
    public static void hi(Serializable arg){
        System.out.println("hi serializable");
    }

    public static void main(String[] args) {
        hi('a');
    }
}
