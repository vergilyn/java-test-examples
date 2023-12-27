package com.vergilyn.sample.springboot.slove02;

import com.vergilyn.sample.springboot.AbstractSingletonTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {
                "spring.application.name=singleton-test-solve-18080"
                , "server.port=18080"
        }
)
public class SingletonSolve02Test1 extends AbstractSingletonTest {


    @SneakyThrows
    @Test
    void test_01() {

        assertExcepted("singleton-test-solve-18080", 18080);

    }
}
