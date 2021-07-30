package com.vergilyn.examples.service;

import java.util.List;

import com.vergilyn.examples.AbstractSpringBootTestApplicationTests;
import com.vergilyn.examples.entity.UserEntity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
// @TestPropertySource("classpath:application-h2.yml")
// @TestPropertySource("classpath:application-h2.properties")
@ActiveProfiles("h2")
public class UserServiceTests extends AbstractSpringBootTestApplicationTests {
    @Autowired
    private UserService testService;

    // spring boot 2.x 依赖 junit 5.x
    @org.junit.jupiter.api.Test
    public void testService() {
        String username = "vergilyn";
        testService.save(username);

        List<UserEntity> users = testService.get(username);
        log.info("users: {}", users);
    }

}
