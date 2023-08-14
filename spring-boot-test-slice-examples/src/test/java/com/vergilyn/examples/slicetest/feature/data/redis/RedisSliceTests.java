package com.vergilyn.examples.slicetest.feature.data.redis;

import com.vergilyn.examples.slicetest.feature.data.UnexpectedService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * {@linkplain DataRedisTest}:
 * <ul>
 *     <li>{@linkplain CacheAutoConfiguration}</li>
 *     <li>{@linkplain RedisAutoConfiguration}</li>
 *     <li>{@linkplain RedisRepositoriesAutoConfiguration}</li>
 * </ul>
 *
 *
 * @author vergilyn
 * @since 2021-05-27
 *
 * @see <a href="https://docs.spring.io/spring-boot/docs/2.2.11.RELEASE/reference/htmlsingle/#boot-features-testing-spring-boot-applications-testing-autoconfigured-redis-test">
 *          Auto-configured Data Redis Tests</a>
 * @see <a href="https://github.com/spring-projects/spring-boot/tree/v2.2.11.RELEASE/spring-boot-project/spring-boot-test-autoconfigure/src/test/java/org/springframework/boot/test/autoconfigure/data/redis">
 *          github, `@DataRedisTest` examples</a>
 */
@DataRedisTest
// @ContextConfiguration(classes = RedisSliceTests.RedisSliceTestConfiguration.class)
public class RedisSliceTests {

	@Autowired(required = false)
	ApplicationContext applicationContext;
	@Autowired(required = false)
	StringRedisTemplate stringRedisTemplate;
	@Autowired(required = false)
	UnexpectedService unexpectedService;

	/**
	 * 1. 如果只使用{@link DataRedisTest @DataRedisTest}，且可扫描package下<b>不包含</b> {@link SpringBootConfiguration}时：
	 * <pre>
	 *   java.lang.IllegalStateException:
	 *     Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test
	 * </pre>
	 *
	 * <p> 解决方式1：package下增加 例如{@link RedisSliceTestApplication}（且被 {@link SpringBootApplication} 注释）。
	 * <p> 解决方式2：通过 {@link ContextConfiguration} 指定 非package目录下的 {@code XxxApplication}。
	 * <p> 解决方式3：使用 嵌套的{@link TestConfiguration} 指定 {@link SpringBootConfiguration} 、{@link EnableAutoConfiguration}等注解。
	 */
	@Test
	public void test(){
		assertThat(applicationContext).isNotNull();
		assertThat(stringRedisTemplate).isNotNull();
		assertThat(unexpectedService).isNull();
	}

	/**
	 * {@link SpringBootApplication} = {@link SpringBootConfiguration} + {@link EnableAutoConfiguration}
	 */
	@TestConfiguration
	@SpringBootConfiguration
	@EnableAutoConfiguration
	// @AutoConfigurationPackage(basePackages = "com.vergilyn.examples.slicetest.data")
	static class RedisSliceTestConfiguration {

	}
}
