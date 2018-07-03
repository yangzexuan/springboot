package com.dome.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DomeSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(DomeSpringBootApplication.class,args);
    }
}
