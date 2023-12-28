package com.vergilyn.sample.springboot.context.case03;

import com.vergilyn.sample.springboot.context.ContextApplication;
import com.vergilyn.sample.springboot.context.ContextHolder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ContextApplication.class, properties = "spring.application.name=context-test-32")
@Order(Integer.MAX_VALUE)
class ContextTest32 {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void test() {
        System.out.printf("[vergilyn] %s context: %s\n", this.getClass().getSimpleName(), applicationContext);

        ContextHolder testHolder = ContextHolder.getInstance();

        assertThat(testHolder.getContext())
                .isNotNull()
                .isNotSameAs(applicationContext);
    }
}
