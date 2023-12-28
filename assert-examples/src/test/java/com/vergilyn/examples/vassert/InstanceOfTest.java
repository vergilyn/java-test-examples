package com.vergilyn.examples.vassert;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InstanceOfTest {

    @Test
    void test(){
        Object object = new Integer(2);

        assertThat(object)
                  .asInstanceOf(InstanceOfAssertFactories.type(Integer.class))
                  .isEqualTo(2);
    }
}
