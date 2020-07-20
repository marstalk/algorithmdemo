package com.marstalk.strace;

/**
 * 对于基本类型操作，Java程序不会调用操作系统函数：
 * 下面这个例子中，整个过程只调用了操作系统的write函数，对应的是System.out.println()方法。
 * write(1, "begin 1 + 1 = 2", 15)         = 15
 * write(1, "\n", 1)                       = 1
 * write(1, "2", 1)                        = 1
 * write(1, "\n", 1)                       = 1
 * write(1, "end 1 + 1 = 2", 13)           = 13
 * write(1, "\n", 1)                       = 1
 */
public class StraceDemo1 {
    public static void main(String[] args) {
        System.out.println("begin 1 + 1 = 2");
        int a = 1;
        int b = 1;
        int c = a + b;
        System.out.println(c);
        System.out.println("end 1 + 1 = 2");
    }
}
