package com.marstalk.pluggableannotationprocessingapi;

/**
 * 测试类，使用自定义注解BuilderProperty注解setter方法。
 * @author shanzhonglaosou
 */
public class Person {
    private int age;
    private String name;
    private String addr;

    public int getAge() {
        return age;
    }

    @BuilderProperty
    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @BuilderProperty
    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}