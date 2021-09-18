package com.vergilyn.examples.mockito.feature.annotation;

import com.vergilyn.examples.mockito.AbstractMockitoSpringBootTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;

/**
 * 场景：<br/>
 * 1) spring-boot main中，通过编码的形式设置profiles{@linkplain org.springframework.boot.SpringApplication#setAdditionalProfiles(String...)}
 * 时，unit-test 并不会执行 main方法，导致 profiles丢失。<br/>
 * 2) 多环境文件（XXX 2021-09-18, 待考虑，是否可行）<br/>
 *
 * @author vergilyn
 * @since 2021-09-18
 *
 * @see org.springframework.test.context.TestPropertySource#properties()
 * @see TestPropertySourceTests
 */
@ActiveProfiles("test")
public class ActiveProfilesTests extends AbstractMockitoSpringBootTests {

	@Value("${spring.application.name}")
	private String applicationName;

	@Test
	public void test() {
		System.out.println(applicationName);
	}
}
