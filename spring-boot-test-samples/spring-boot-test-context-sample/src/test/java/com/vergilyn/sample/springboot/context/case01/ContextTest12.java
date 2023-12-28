package com.vergilyn.sample.springboot.context.case01;

import com.vergilyn.sample.springboot.context.ContextHolder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Order(Integer.MAX_VALUE)
class ContextTest12 extends AbstractContextTest {

    @Test
    void test() {
        printContext();

        ContextHolder test11Holder = ContextHolder.getInstance();

        assertThat(test11Holder.getContext())
                .isNotNull()
                .isSameAs(applicationContext);
    }
}
