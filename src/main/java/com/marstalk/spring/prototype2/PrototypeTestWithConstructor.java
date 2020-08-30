package com.marstalk.spring.prototype2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ComponentScan("com.marstalk.spring.prototype2")
@Configuration
public class PrototypeTestWithConstructor {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PrototypeTestWithConstructor.class);
        System.out.println(">>>>>>>>>>>>>>>finish ctx>>>>>>>>>>>>>>>");
        Prototye p1 = ctx.getBean(Prototye.class);
        Prototye p2 = ctx.getBean(Prototye.class);

        // 原始类型，肯定不是同一个对象。
        System.out.println("p1 == p2 " + (p1 == p2));
        // 但是他依赖的单例，是同一个对象。
        System.out.println("p1.s == p2.s " + (p1.singleton == p2.singleton));

        Singleton s1 = ctx.getBean(Singleton.class);
        Singleton s2 = ctx.getBean(Singleton.class);

        // 单例，肯定是同一个对象。
        System.out.println("s1 == s2 " + (s1 == s2));
        // 单例依赖的原始类型，也是同一个。
        System.out.println("s1.p == s2.p " + (s1.prototye == s2.prototye));

    }
}

@Component
class Singleton {
    Prototye prototye;

    public Singleton(Prototye prototye) {
        this.prototye = prototye;
    }
}

@Component
@Scope("prototype")
class Prototye {
    Singleton singleton;

    public Prototye(Singleton singleton) {
        this.singleton = singleton;
    }
}