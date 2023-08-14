package com.vergilyn.examples.slicetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SliceTestApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SliceTestApplication.class);
        application.run(args);
    }

}
