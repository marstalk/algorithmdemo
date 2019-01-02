package com.marstalk.basic.typeint;

/**
 * 实线method方法，使得，使得程序运行后输出：
 * 100
 * 100
 */
public class PassingByValue {
    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        method(a, b);
        System.out.println(a);
        System.out.println(b);
    }

    private static void method(int a, int b) {

    }
}
