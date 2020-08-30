package com.marstalk.spring.factorybean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.testng.Assert;
import org.testng.annotations.Test;

@ComponentScan("com.marstalk.spring.factorybean")
public class FactoryBeanTest {

    @Test
    public void t1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FactoryBeanTest.class);
        System.out.println("context ready");
        UserMapper bean = context.getBean(UserMapper.class);
        UserMapper bean1 = context.getBean(UserMapper.class);
        System.out.println(bean == bean1);
        bean.update();

        UserService bean2 = context.getBean(UserService.class);
        UserService bean3 = context.getBean(UserService.class);
        System.out.println(bean2 == bean3);

        bean2.update();
    }

}
