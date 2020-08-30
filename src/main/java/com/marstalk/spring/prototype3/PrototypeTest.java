package com.marstalk.spring.prototype3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ComponentScan("com.marstalk.spring.prototype3")
@Configuration
public class PrototypeTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PrototypeTest.class);
        System.out.println(">>>>>>>>>>>>>>>finish ctx>>>>>>>>>>>>>>>");
        B p1 = ctx.getBean(B.class);
        B p2 = ctx.getBean(B.class);

        //原始类型，肯定不是同一个对象。
        System.out.println("p1 == p2 " + (p1 == p2));
        //但是他依赖的单例，是同一个对象。
        System.out.println("p1.s == p2.s " + (p1.singleton == p2.singleton));

        A s1 = ctx.getBean(A.class);
        A s2 = ctx.getBean(A.class);

        //单例，肯定是同一个对象。
        System.out.println("s1 == s2 " + (s1 == s2));
        //单例依赖的原始类型，也是同一个。
        System.out.println("s1.p == s2.p " + (s1.b == s2.b));

    }
}

@Component
class A {
    B b;

    public A(B b) {
        this.b = b;
    }
}

@Component
class B {
    @Autowired A singleton;
}