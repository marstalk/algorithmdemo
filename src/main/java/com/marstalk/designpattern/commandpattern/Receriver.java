package com.marstalk.designpattern.commandpattern;

/**
 * 接受者
 */
public class Receriver {
    public void action(){
        //真正的业务逻辑
        System.out.println("do real business1");
    }
    public void action2(){
        //真正的业务逻辑
        System.out.println("do real business2");
    }
}
