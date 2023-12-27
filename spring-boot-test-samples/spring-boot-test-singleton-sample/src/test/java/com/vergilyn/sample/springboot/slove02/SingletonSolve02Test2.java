package com.vergilyn.sample.springboot.slove02;

import com.vergilyn.sample.springboot.AbstractSingletonTest;
import com.vergilyn.sample.springboot.configuration.SingletonContext;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {
                "spring.application.name=singleton-test-solve-28080"
                , "server.port=28080"
        }
)
// 因为 是在spring-context创建过程中初始化的单例模式对象，所以还需要依赖`@DirtiesContext`
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SingletonSolve02Test2 extends AbstractSingletonTest {

    @BeforeAll
    public void beforeAll(){
        ReflectionTestUtils.setField(SingletonContext.class, "_instance", null);
    }


    @SneakyThrows
    @Test
    void test_02() {

        assertExcepted("singleton-test-solve-28080", 28080);

    }
}