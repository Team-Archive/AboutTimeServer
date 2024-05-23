package com.aboutTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.aboutTime")
public class AboutTimeWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AboutTimeWebApplication.class, args);
    }
}