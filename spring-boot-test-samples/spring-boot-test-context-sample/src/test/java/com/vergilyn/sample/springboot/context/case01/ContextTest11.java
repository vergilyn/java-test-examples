package com.vergilyn.sample.springboot.context.case01;

import com.vergilyn.sample.springboot.context.ContextHolder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Order(Integer.MIN_VALUE)
class ContextTest11 extends AbstractContextTest {

    @Test
    void test() {
        printContext();

        ContextHolder contextHolder = ContextHolder.getInstance(applicationContext);
        assertThat(contextHolder.getContext()).isNotNull();
    }
}
