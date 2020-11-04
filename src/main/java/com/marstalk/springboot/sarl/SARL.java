package com.marstalk.springboot.sarl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.marstalk.springboot.sarl"})
public class SARL {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SARL.class);
        springApplication.run();
    }

}
