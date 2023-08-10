package com.vergilyn.examples.slicetest;

import com.vergilyn.examples.slicetest.entity.UserEntity;
import com.vergilyn.examples.slicetest.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SliceTestApplication implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SliceTestApplication.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(applicationContext.getEnvironment().getProperty("spring.application.name"));

        UserEntity byId = userService.getById(1);
        System.out.println(byId);
    }
}
