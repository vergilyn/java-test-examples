package com.vergilyn.examples.slicetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.vergilyn.examples.slicetest.controller")
public class SliceTestApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SliceTestApplication.class);
        application.run(args);
    }

}
