package com.vergilyn.sample.springboot.reproduce;

import com.vergilyn.sample.springboot.AbstractSingletonTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {
                "spring.application.name=singleton-test-28080"
                , "server.port=28080"
        }
)
// 虽然会重新创建新的 spring-context。但是无法解决 单例模式 带来的问题
// @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class SingletonTest2 extends AbstractSingletonTest {


    @SneakyThrows
    @Test
    void test_02() {

        assertExcepted("singleton-test-28080", 28080);

    }
}