package com.marstalk.instanceOf;

/**
 * @author Mars
 * Created on 11/17/2019
 */
public class Main {

    public static void main(String[] args) {

        System.out.println(Son.class.isAssignableFrom(Object.class));


    }
}

interface person {
}

class Father implements person {
}

class Son extends Father implements person {
}

class Cat {

}