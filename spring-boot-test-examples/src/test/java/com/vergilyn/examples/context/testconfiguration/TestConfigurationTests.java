package com.vergilyn.examples.context.testconfiguration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * <p>
 *   <a href="https://docs.spring.io/spring-boot/docs/2.2.11.RELEASE/reference/htmlsingle/#boot-features-testing-spring-boot-applications-excluding-config">2.2.11.RELEASE, boot-features-testing, Excluding Test Configuration</a>
 *   <pre>
 *     {@linkplain TestConfiguration} can be used on an <b>inner class of a test</b> to customize the primary configuration.
 *     <b>When placed on a top-level class</b>, {@linkplain TestConfiguration} indicates that classes in {@code `src/test/java`}
 *     <b>should not be picked up by scanning</b>.
 *
 *     You can then import that class explicitly where it is required, as shown in the following example: {@linkplain Import}
 *   </pre>
 *
 * </p>
 *
 * <p>
 *     完全没理解文档说的是什么意思...(还是主要看 2.2.11 的描述) <br/>
 *     1. 被`@TestConfiguration`修饰的 top-level-class <b>不会</b> 被auto-register。<br/>
 *     2. 被`@TestConfiguration`修饰的 inner-class <b>可以</b> 被auto-register。<br/>
 *     3. 可以通过`@Import`显式的register需要的class。
 * </p>
 *
 *
 *
 * <p>根据测试情况的个人理解 {@linkplain TestConfiguration}：<br/>
 *   1. 测试类中 的静态内部类如果被`{@linkplain TestConfiguration}`修饰，则可以被 registered-automatically。 <br/>
 *   2. 即使是相同package下的class (eg. {@linkplain OuterClassTestConfiguration})，无法被 auto-registry。
 *     则可以满足 “单元测试中按需注入bean”。<br/>
 *   3. 如果全部用 inner-class 可能代码很难看，所以可以结合 {@linkplain ContextConfiguration} 和 {@linkplain Import} 按需依赖。<br/>
 *   4. {@linkplain ContextConfiguration} 和 {@linkplain Import} 表现上的区别
 *   <pre>
 *   `@ContextConfiguration(classes)`:
 *     会导致{@linkplain InnerClassTestConfiguration}没有被 registered。(eg. `inner == null`)
 *     解决：在{@linkplain OuterClassTestConfiguration}`中添加 {@linkplain ComponentScan}
 *
 *   `@Import(...)`:
 *     inner: 如果用`@TestConfiguration`或`@Configuration`注释的类是 **测试类中的静态嵌套类**，则它将被 registered-automatically。
 *     outer: 通过`@Import`显式的register。
 *
 *    结论：
 *      `@ContextConfiguration`更多的是用来决定如何 加载和配置 ApplicationContext
 *   </pre>
 *
 * </p>
 *
 *
 * @author vergilyn
 * @since 2021-09-30
 *
 * @see OuterClassTestConfiguration
 * @see <a href="https://docs.spring.io/spring-boot/docs/2.2.11.RELEASE/reference/htmlsingle/#boot-features-testing-spring-boot-applications-excluding-config">2.1.1.RELEASE, Excluding Test Configuration</a>
 * @see <a href="https://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#boot-features-testing-spring-boot-applications-excluding-config">1.5.1.RELEASE, Excluding test configuration</a>
 * @see com.vergilyn.examples.context.testcomponent.TestComponentTests
 */
@SpringBootTest
// @ContextConfiguration(classes = OuterClassTestConfiguration.class)
// @Import(OuterClassTestConfiguration.class)
public class TestConfigurationTests {

	@Autowired(required = false)
	@Qualifier("innerClass")
	private String innerClass;

	@Autowired(required = false)
	@Qualifier("outerClass")
	private String outerClass;

	@TestConfiguration
	static class InnerClassTestConfiguration {

		@Bean("innerClass")
		public String inner(){
			return InnerClassTestConfiguration.class.getName();
		}
	}

	@Test
	public void test(){
		System.out.println("inner-class >>>> " + innerClass);
		System.out.println("outer-class >>>> " + outerClass);

		Class<?> thisClass = this.getClass();
		ContextConfiguration contextConfiguration;
		ComponentScan componentScan = null;
		Import imp;

		contextConfiguration = AnnotationUtils.findAnnotation(thisClass, ContextConfiguration.class);
		if (contextConfiguration != null){
			componentScan = AnnotationUtils.findAnnotation(contextConfiguration.classes()[0],
			                                                                ComponentScan.class);
		}
		imp = AnnotationUtils.findAnnotation(thisClass, Import.class);

		if (contextConfiguration == null){
			if (imp == null){
				System.out.println("`contextConfiguration == null` && `imp == null` >>>> "
						                   + "expected, `inner-class != null`, `outer-class == null`");
				assertNotNull(innerClass);
				assertNull(outerClass);
			}else {
				System.out.println("`contextConfiguration == null` && `imp != null` >>>> "
						                   + "expected, `inner-class != null`, `outer-class != null`");
				assertNotNull(innerClass);
				assertNotNull(outerClass);
			}
			return;
		}

		if (componentScan == null){
			System.out.println("`contextConfiguration != null` && `componentScan == null` >>>> "
					                   + "expected, `inner-class == null`, `outer-class != null`");
			assertNull(innerClass);
			assertNotNull(outerClass);
		}else {
			System.out.println("`contextConfiguration != null` && `componentScan != null` >>>> "
					                   + "expected, `inner-class != null`, `outer-class != null`");
			assertNotNull(innerClass);
			assertNotNull(outerClass);
		}
	}

}
