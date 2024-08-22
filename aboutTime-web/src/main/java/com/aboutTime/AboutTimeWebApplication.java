package com.aboutTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "com.aboutTime")
public class AboutTimeWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AboutTimeWebApplication.class, args);
    }
}