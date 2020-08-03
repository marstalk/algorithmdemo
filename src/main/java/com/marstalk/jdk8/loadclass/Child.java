package com.marstalk.jdk8.loadclass;

public class Child extends Father {
    private static int ii = 2;
    static {
        System.out.println("2");
    }

    private static Print print = new Print("child's static print");

    private Print dynamicPrint = new Print("child's dynamic print");

    public Child(Print dynamicPrint) {
        System.out.println("child's constructor");
    }

    public Child(Print dynamicPrint, int i){
        System.out.println("child's constructor with two parameters");
    }

    {
        System.out.println("this is child's dynamic code block");
    }
}
