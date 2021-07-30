package com.vergilyn.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * <p> 2020-04-27
 *   springboot-test虽然可以指定启动类{@linkplain SpringBootTestApplication}，但是并不会执行{@linkplain SpringBootTestApplication#main(String[])}。
 *   所以可能导致导致`application-{profile}`未正常加载
 *   （当然可以不用{@linkplain SpringApplication#setAdditionalProfiles(String...)}，而在application.properties中配置）
 *
 * <p>
 *   解决方式一：{@linkplain TestPropertySource}加载配置文件（更多的用于加载外部配置文件，而非 active-profile），但是貌似不支持`.yml`形式。
 *   <br/><b>2021-07-30，高版本的spring-boot-2.2.11.RELEASE，已经支持`.yml`</b>
 * </p>
 *
 * <br/>
 * <p>
 *   解决方式二：{@linkplain org.springframework.test.context.ActiveProfiles}，（根据命名和该场景下）更推荐此写法。
 * </p>
 *
 * @author vergilyn
 * @date 2020-04-27
 * @see <a href="https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#boot-features-testing">boot-features-testing</a>
 */
@SpringBootTest(classes = SpringBootTestApplication.class)
// @TestPropertySource(locations = {"/application-{profile}.properties"})
// @ActiveProfiles("{profile}")
@Slf4j
public abstract class AbstractSpringBootTestApplicationTests {

	@Autowired
	protected AnnotationConfigApplicationContext applicationContext;

	protected <T> T getBean(Class<T> clazz){
		try {
			return applicationContext.getBean(clazz);
		}catch (NoSuchBeanDefinitionException e){
			log.error("NoSuchBeanDefinitionException >>>> {}", e.getMessage());
			return null;
		}
	}

	protected Object getBean(String beanName){
		try {
			return applicationContext.getBean(beanName);
		}catch (NoSuchBeanDefinitionException e){
			log.error("NoSuchBeanDefinitionException >>>> {}", e.getMessage());
			return null;
		}
	}
}
