package com.marstalk.interview.dingqiao;

/**
 * 享元模式
 */
public class InterviewQuestion7Boxing {
    public static void main(String[] args) {
        //invokestatic #2 <java/lang/Integer.valueOf>
        Integer a1 = 100;
        Integer a2 = 100;

        Integer b1 = 1000;
        Integer b2 = 1000;

        //a1 == a2 : true
        //b1 == b2 : false
        System.out.println("a1 == a2 : " + (a1 == a2));
        System.out.println("b1 == b2 : " + (b1 == b2));
    }
}
