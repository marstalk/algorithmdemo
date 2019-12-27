package com.marstalk.algorithmdemo;

/**
 * 不用循环和本地方法，打印如下：2n
 * 1123
 * 2246
 * 4492
 * 4492
 * 2246
 * 1123
 *
 * @author Mars
 * Created on 11/9/2019
 */
public class RecursionDemo {

    public static void main(String[] args) {
        doublePrint(1123);
    }

    private static void doublePrint(int i) {
        if (i > 5000) {
            return;
        }
        System.out.println(i);
        doublePrint(i << 1);
        System.out.println(i);
    }
}
