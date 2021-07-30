package com.vergilyn.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * <p> 2020-04-27
 *   springboot-test虽然可以指定启动类{@linkplain SpringBootTestApplication}，但是并不会执行{@linkplain SpringBootTestApplication#main(String[])}。
 *   所以可能导致导致`application-{profile}`未正常加载
 *   （当然可以不用{@linkplain SpringApplication#setAdditionalProfiles(String...)}，而在application.properties中配置）
 *
 * <p>
 *   另外一种方式是：{@linkplain TestPropertySource}记载配置文件，但是貌似不支持`.yml`形式。
 *   <br/><b>2021-07-30，高版本的spring-boot-2.2.11.RELEASE，已经支持`.yml`</b>
 * </p>
 *
 * @author vergilyn
 * @date 2020-04-27
 * @see <a href="https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#boot-features-testing">boot-features-testing</a>
 */
@SpringBootTest(classes = SpringBootTestApplication.class)
// @TestPropertySource(locations = {"/application-{profile}.properties"})
public abstract class AbstractSpringBootTestApplicationTests {
}
