package com.marstalk.jdk8.jvm.chapter8.p3;

public class StaticDispatch2 {
    public static void main(String[] args) {
        hi();
    }
    private static void hi(){
        int a = 1;
    }

    public void virtualMethod(){
        privateMethod();
    }
    private void privateMethod(){

    }
}
