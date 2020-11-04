package com.marstalk.springboot.aci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ACI {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ACI.class);
        springApplication.run(args);
    }

}
