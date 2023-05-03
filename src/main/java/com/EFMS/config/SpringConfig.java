package com.EFMS.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EntityScan("com.EFMS")
@ComponentScan(basePackages = {"com.EFMS"})
@Configuration
public class SpringConfig extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SpringConfig.class, args);
    }
}
