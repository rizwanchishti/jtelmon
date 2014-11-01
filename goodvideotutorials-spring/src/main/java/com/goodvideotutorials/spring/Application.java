/*
 * Run As -> Spring Boot App or Java Application 
 * https://www.youtube.com/watch?v=gJM3Tg9dSDc
 * 
 */

package com.goodvideotutorials.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
