package com.vergilyn.examples.controller;

import java.net.URI;

import com.vergilyn.examples.AbstractSpringBootTestApplicationTests;
import com.vergilyn.examples.SpringBootTestApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 * <pre>
 * <a href="https://docs.spring.io/spring-boot/docs/2.2.11.RELEASE/reference/html/spring-boot-features.html#boot-features-testing-spring-boot-applications-testing-with-running-server">
 *     Testing with a running server </a>
 *
 * > If you need to <b>start a full running server</b>, we recommend that you use random ports.
 * > If you use `@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)`,
 * > an available port is picked at random each time your test runs.
 * > See Also: {@linkplain LocalServerPort}
 * </pre>
 *
 * @author vergilyn
 * @since 2021-12-01
 *
 * @see LocalServerPort
 */
@SpringBootTest(classes = SpringBootTestApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RandomPortTestRestTemplateExampleTests extends AbstractSpringBootTestApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void exampleTest(@Autowired TestRestTemplate restTemplate) {
		String url = "/user/get";

		String resp = restTemplate.getForObject(url, String.class);
		System.out.printf("[vergilyn] >>>> resp: %s \n", resp);

		// 这种写法不友好，构造`URI`的方法有很多
		URI uri = new DefaultUriBuilderFactory().uriString(url).queryParam("username", "schema").build();
		resp = restTemplate.getForObject(uri, String.class);

		System.out.printf("[vergilyn] >>>> resp: %s \n", resp);
	}
}
