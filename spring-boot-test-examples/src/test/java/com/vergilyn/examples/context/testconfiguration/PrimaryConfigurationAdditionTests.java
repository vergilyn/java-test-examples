package com.vergilyn.examples.context.testconfiguration;

import com.vergilyn.examples.AbstractSpringBootTestApplicationTests;
import com.vergilyn.examples.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

// @ContextConfiguration(classes = PrimaryConfigurationAdditionTests.InnerTestConfiguration.class)
public class PrimaryConfigurationAdditionTests extends AbstractSpringBootTestApplicationTests {

    /**
     * 嵌套的 @TestConfiguration 类 是<b>附加</b>到 primary-configuration（而不是替代）
     */
    @Test
    void test_TestConfiguration_is_addition() {
        assertThat(applicationContext.getBeansOfType(UserService.class)).hasSize(2);
        assertThat(applicationContext.getBean(UserService.BEAN_NAME, UserService.class));
        assertThat(applicationContext.getBean("additionUserService", UserService.class));

        assertThat(applicationContext.getBean("additionString", String.class)).isEqualTo("addition-string");
    }

    @TestConfiguration
    static class InnerTestConfiguration {

        // 无法重复定义相同名字的bean，会导致异常
        /*@Bean(UserService.BEAN_NAME)
        public UserService userService(){
            return new UserService("addition-UserService");
        }*/

        @Bean("additionUserService")
        public UserService additionUserService(){
            return new UserService("additionUserService");
        }

        @Bean
        public String additionString(){
            return "addition-string";
        }
    }
}
