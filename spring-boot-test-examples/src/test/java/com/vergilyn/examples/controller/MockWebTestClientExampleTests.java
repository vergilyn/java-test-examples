package com.vergilyn.examples.controller;

import com.vergilyn.examples.AbstractSpringBootTestApplicationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * 相对于 {@linkplain  MockMvcExampleTests} 的{@linkplain org.springframework.test.web.servlet.MockMvc}
 *
 * <pre>
 *  > If you want to focus only on the web layer and not start a complete ApplicationContext,
 *  > consider using `@WebMvcTest` instead.
 * </pre>
 *
 * @see AutoConfigureMockMvc
 * @see AutoConfigureWebTestClient
 */
// `AutoConfigureWebTestClient` Annotation that can be applied to a test class to enable a WebTestClient.
// At the moment, only WebFlux applications are supported. (详见`@AutoConfigureWebTestClient` javadoc)
@AutoConfigureWebTestClient
public class MockWebTestClientExampleTests extends AbstractSpringBootTestApplicationTests {
	// FIXME 2021-12-01 "No qualifying bean of type 'org...WebTestClient' available: expected at least 1 bean"
	@Autowired
	private WebTestClient webClient;

	@Test
	void exampleTest() {
		webClient.get().uri("/helloworld/get")
				.exchange().expectStatus().isOk()
				.expectBody(String.class).isEqualTo("Hello World");
	}
}
