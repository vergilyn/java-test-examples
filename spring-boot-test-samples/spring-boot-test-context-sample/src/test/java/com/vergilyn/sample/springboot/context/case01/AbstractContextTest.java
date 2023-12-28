package com.vergilyn.sample.springboot.context.case01;

import com.vergilyn.sample.springboot.context.ContextApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = ContextApplication.class)
abstract class AbstractContextTest {

    @Autowired
    protected ApplicationContext applicationContext;

    protected final void printContext(){
        System.out.printf("[vergilyn] %s context: %s\n", this.getClass().getSimpleName(), applicationContext);
    }
}
