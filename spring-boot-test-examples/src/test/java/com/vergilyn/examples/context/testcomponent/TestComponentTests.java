package com.vergilyn.examples.context.testcomponent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.ApplicationContext;

/**
 * <p>
 *   <a href="https://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#boot-features-testing-spring-boot-applications-excluding-config">boot-features-testing, Excluding test configuration</a>
 *   <pre>
 *     If your application uses component scanning, for example if you use {@code `@SpringBootApplication`} or {@code `@ComponentScan`},
 *     you may find components or configurations created only for specific tests accidentally get picked up everywhere.
 *
 *     To help prevent this, Spring Boot provides {@code `@TestComponent`} and {@code `@TestConfiguration`} annotations
 *     that can be used on classes in {@code `src/test/java`} to indicate that they should not be picked up by scanning.
 *
 *     {@code `@TestComponent`} and {@code @TestConfiguration} are <b>only needed on top level classes.</b>
 *     If you define {@code `@Configuration`} or {@code `@Component`} as inner-classes within a test
 *     (any class that has {@code `@Test`} methods or {@code `@RunWith`}), they will be automatically filtered.
 *
 *     If you directly use {@code `@ComponentScan`} (i.e. not via {@code `@SpringBootApplication`})
 *     you will need to register the TypeExcludeFilter with it. See <a href="https://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/api/org/springframework/boot/context/TypeExcludeFilter.html">the Javadoc</a> for details.
 *   </pre>
 * </p>
 *
 * <p>
 *   TODO 2021-09-30 完全看不懂，也无法理解`@TestComponent`到底是做什么的.........
 *   并且`2.2.11.RELEASE`文档中都没找到 `@TestComponent`相关的描述（只找到了`@TestConfiguration`）。
 * </p>
 *
 * @author vergilyn
 * @since 2021-09-30
 *
 * @see TestComponent
 * @see org.springframework.boot.context.TypeExcludeFilter
 * @see OuterClassTestComponent
 * @see <a href="https://docs.spring.io/spring-boot/docs/2.2.11.RELEASE/reference/htmlsingle/#boot-features-testing-spring-boot-applications-excluding-config">Excluding Test Configuration</a>
 * @see com.vergilyn.examples.context.testconfiguration.TestConfigurationTests
 */
@SpringBootTest
public class TestComponentTests {
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired(required = false)
	private InnerClassTestComponent innerClassTestComponent;
	@Autowired(required = false)
	private OuterClassTestComponent outerClassTestComponent;

	@TestComponent
	public static class InnerClassTestComponent {

	}

	@Test
	public void test() {
		System.out.println("inner-class >>>> " + innerClassTestComponent);
		System.out.println("outer-class >>>> " + outerClassTestComponent);

	}


}
