package com.marstalk.jdk8.jvm.chapter8.p3;

public class StaticDispatch {
    static abstract class Human{}
    static class Man extends Human{}
    static class Woman extends Human{}

    public void sayHi(Human guy){
        System.out.println("hello, guy!");
    }
    public void sayHi(Man man){
        System.out.println("hello, man");
    }
    public void sayHi(Woman woman){
        System.out.println("hello, woman");
    }
    public static void main(String[] args) {
        StaticDispatch sr = new StaticDispatch();
        Human man = new Man();
        Human woman = new Woman();
        sr.sayHi(man);
        sr.sayHi(woman);
    }
}
