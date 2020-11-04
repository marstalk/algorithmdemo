package com.marstalk.spring.annotationimport;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 验证Import的第二种用法
 * AnnotationImportBeanDefinitionRegistrar要实现ImportSelector
 * //TODO
 */
@Configuration
@Import(AnnotationImportSelector.class)
public class AnnotationImport2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotationImport2.class);
        for (String beanDefinitionName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        ConfigurableEnvironment environment = ctx.getEnvironment();
        String property = environment.getProperty("syn.name");
        System.out.println(property);
    }
}
