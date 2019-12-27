package com.marstalk.concurrent.threadlocal;

public class NameHolder {
    private static ThreadLocal<String> nameTL = new ThreadLocal<>();
    private static ThreadLocal<String> tl2 = new ThreadLocal<>();

    public static String getName() {
        return nameTL.get();
    }
    public static void setName(String name){
        nameTL.set(name);
    }
    public static void clear(){
        nameTL.remove();
    }

    public static void main(String[] args) {
        NameHolder.setName("main");

        new Thread(()->{
            NameHolder.setName("sub");
            NameHolder.getName();
            NameHolder.clear();
        }).start();

        NameHolder.getName();
        NameHolder.clear();

    }
}
