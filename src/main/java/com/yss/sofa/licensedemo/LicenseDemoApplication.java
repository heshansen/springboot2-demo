package com.yss.sofa.licensedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LicenseDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicenseDemoApplication.class, args);
    }
}
