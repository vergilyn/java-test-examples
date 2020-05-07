package com.vergilyn.examples;

import java.util.List;

import com.vergilyn.examples.common.JavaTestApplication;
import com.vergilyn.examples.common.entity.UserEntity;
import com.vergilyn.examples.common.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * testng 需要继承 {@linkplain AbstractTestNGSpringContextTests}，之后就可以结合 {@linkplain SpringBootTest}
 * @author vergilyn
 * @date 2020-05-07
 * @see <a href="https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#boot-features-testing">boot-features-testing</a>
 */
@SpringBootTest(classes = JavaTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Slf4j
public class SpringbootTestng extends AbstractTestNGSpringContextTests {
    @Autowired
    private UserService testService;

    // spring boot 2.x 依赖 junit 5.x
    @org.testng.annotations.Test
    public void testService() {
        String username = "vergilyn";
        testService.save(username);

        List<UserEntity> users = testService.get(username);
        log.info("users: {}", users);
    }

}
