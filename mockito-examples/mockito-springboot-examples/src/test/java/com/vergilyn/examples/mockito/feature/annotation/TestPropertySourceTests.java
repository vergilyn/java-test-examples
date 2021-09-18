package com.vergilyn.examples.mockito.feature.annotation;

import com.vergilyn.examples.mockito.AbstractMockitoSpringBootTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;

/**
 * 可以通过 {@linkplain TestPropertySource} 灵活的修改 env。
 *
 * <p/>
 * 场景：<br/>
 *   1) 例如 mock 测试RPC方法时(spring-cloud-openFeign/dubbo)，不希望强依赖 registry-center 或者 service-provider 的运行状态。
 *   （不可用时，也可以正常进行 mock-unit-test）
 *
 * @author vergilyn
 * @since 2021-09-18
 *
 * @see org.springframework.test.context.ActiveProfiles
 * @see ActiveProfilesTests
 */
@TestPropertySource(properties = "spring.application.name = test-property-source-tests")
public class TestPropertySourceTests extends AbstractMockitoSpringBootTests {

	@Value("${spring.application.name}")
	private String applicationName;

	@Test
	public void test(){
		System.out.println(applicationName);
	}
}
