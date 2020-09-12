package com.marstalk.spring.jdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackages = {"com.marstalk.spring.jdbcTemplate"})
public class JdbcTemplateApplication {
    @Autowired
    JdbcService jdbcService;

    public static void main(String[] args) {
        SpringApplication jdbcTemplateApplication = new SpringApplication(JdbcTemplateApplication.class);
        ConfigurableApplicationContext context = jdbcTemplateApplication.run(args);
        JdbcService jdbcService = context.getBean(JdbcService.class);
        jdbcService.query();
    }

}
