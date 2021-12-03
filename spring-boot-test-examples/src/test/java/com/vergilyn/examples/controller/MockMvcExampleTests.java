package com.vergilyn.examples.controller;

import com.vergilyn.examples.AbstractSpringBootTestApplicationTests;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * <pre>
 *  <a href="https://docs.spring.io/spring-boot/docs/2.2.11.RELEASE/reference/html/spring-boot-features.html#boot-features-testing-spring-boot-applications-testing-with-mock-environment">
 *    MockMvc/a>
 *
 *  > By default, `@SpringBootTest` does not start the server
 *  >（不要指定 `webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT`）.
 *  > If you have web endpoints that you want to test against this mock environment,
 *  > you can additionally configure `MockMvc` as shown in the following example:
 * </pre>
 *
 * 虽然是mock，但会真实的调用对应的controller方法。
 *
 * @author vergilyn
 * @since 2021-12-01
 *
 * @see AutoConfigureMockMvc
 * @see AutoConfigureWebTestClient
 */
@AutoConfigureMockMvc  // 貌似等价于下面的注释代码
class MockMvcExampleTests extends AbstractSpringBootTestApplicationTests {

	//region 2021-12-01 貌似等价于`@AutoConfigureMockMvc`
	/*
	@Autowired
	protected WebApplicationContext webApplicationContext;

	@BeforeAll  // @TestInstance(TestInstance.Lifecycle.PER_CLASS)
	void before(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	*/
	//endregion
	@Autowired
	protected MockMvc mockMvc;

	@SneakyThrows
	@Test
	public void normal(){
		String url = "/user/get";

		mockMvc.perform(MockMvcRequestBuilders.get(url)
				                .param("username", "schema"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Hello World"));
	}

	@SneakyThrows
	@Test
	public void get(){
		//准备请求url  不用带ip、端口、项目名称等 直接写接口的映射地址就可以了
		String url = "/helloworld/get";

		/* 构建request 发送请求GET请求
		 * MockMvcRequestBuilders 中有很多 请求方式。像get、post、put、delete等等
		 */
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
		                                        .accept(MediaType.APPLICATION_OCTET_STREAM)) // 断言返回结果是json
											.andReturn();// 得到返回结果

		// 返回结果
		MockHttpServletResponse response = mvcResult.getResponse();

		System.out.printf("[vergilyn] >>>> status: '%d', resp-content: '%s' \n",
		                  response.getStatus(), response.getContentAsString());
	}

}