package com.vergilyn.examples.testng.springboot;

import java.time.LocalTime;
import java.util.List;

import javax.annotation.Resource;

import com.vergilyn.examples.testng.TestngSpringBootApplication;
import com.vergilyn.examples.testng.entity.UserEntity;
import com.vergilyn.examples.testng.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * testng 需要继承 {@linkplain AbstractTestNGSpringContextTests}，之后就可以结合 {@linkplain SpringBootTest}
 * @author vergilyn
 * @date 2020-05-07
 * @see <a href="https://docs.spring.io/spring-boot/docs/2.2.11.RELEASE/reference/htmlsingle/#boot-features-testing">boot-features-testing</a>
 */
@SpringBootTest(classes = TestngSpringBootApplication.class)
@ActiveProfiles("h2")
@Slf4j
public class UserServiceTestng extends AbstractTestNGSpringContextTests {

	@Resource
	private UserService testService;

	@org.testng.annotations.Test
	public void testService() {
		String username = "vergilyn";
		testService.save(username);

		List<UserEntity> users = testService.get(username);
		System.out.printf("[vergilyn][%s] >>>> users: %s", LocalTime.now(), users);
	}
}
