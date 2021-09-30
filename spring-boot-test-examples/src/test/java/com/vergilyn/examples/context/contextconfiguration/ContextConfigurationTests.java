package com.vergilyn.examples.context.contextconfiguration;

import com.vergilyn.examples.AbstractSpringBootTestApplicationTests;
import com.vergilyn.examples.service.UserService;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor;
import org.springframework.scheduling.annotation.AsyncConfigurationSelector;
import org.springframework.scheduling.annotation.ProxyAsyncConfiguration;
import org.springframework.scheduling.config.TaskManagementConfigUtils;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 有时单元测试，不喜欢注入过多的bean，只期望按需注入。
 *
 * @author vergilyn
 * @since 2021-07-30
 */
public class ContextConfigurationTests{

	@ContextConfiguration(classes = CustomContextConfiguration.class)
	@Import({ContextConfigurationTests.CustomService.class})
	@Nested
	public static class CustomContextConfigurationTests extends AbstractSpringBootTestApplicationTests {
		@Test
		public void test(){
			UserService userService = getBean(UserService.class);
			assertThat(userService).isNotNull();

			CustomService customService = getBean(CustomService.class);
			assertThat(customService).isNotNull();

			/**
			 * 通过`@ContextConfiguration(classes = CustomContextConfiguration.class)`中的 `@EnableAsync`注入
			 *
			 * {@link AsyncConfigurationSelector#selectImports(org.springframework.context.annotation.AdviceMode)}
			 * {@link ProxyAsyncConfiguration}
			 */
			AsyncAnnotationBeanPostProcessor bean = (AsyncAnnotationBeanPostProcessor) getBean(TaskManagementConfigUtils.ASYNC_ANNOTATION_PROCESSOR_BEAN_NAME);
			assertThat(bean).isNotNull();
		}
	}

	@Nested
	public static class NoContextConfigurationTests extends AbstractSpringBootTestApplicationTests {
		@Test
		public void test(){
			// not-null, 因为是`SpringBootTestApplication.class`扫描注入的！
			// remark，可以通过`SpringBootTestApplication.class`中 baseScanPackages 扫描一个不存在的 packages，默认不注入任何业务BEAN。或者`@TestComponent`
			UserService userService = getBean(UserService.class);
			assertThat(userService).isNotNull();

			// null, 因为并没有 @Import(CustomService.class)
			CustomService customService = getBean(CustomService.class);
			assertThat(customService).isNull();

			// null, 因为并没有`@EnableAsync`
			AsyncAnnotationBeanPostProcessor bean = (AsyncAnnotationBeanPostProcessor) getBean(TaskManagementConfigUtils.ASYNC_ANNOTATION_PROCESSOR_BEAN_NAME);
			assertThat(bean).isNull();
		}
	}



	public static class CustomService{

		public String get(){
			return "vergilyn";
		}
	}
}
