package com.academy.mobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * The Application starter using spring boot engine
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@Import(WebSecurityConfig.class)
public class Application {
    /**
     * Entry point for running application
     *
     * @param args additional running params
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}