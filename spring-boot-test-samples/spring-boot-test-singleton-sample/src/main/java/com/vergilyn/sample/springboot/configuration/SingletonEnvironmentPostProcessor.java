package com.vergilyn.sample.springboot.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class SingletonEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String applicationName = environment.getProperty("spring.application.name");
        Integer serverPort = environment.getProperty("server.port", Integer.class);

        SingletonContext singletonContext = SingletonContext.getInstance(applicationName, serverPort);
        log.info(" {} >>>> singleton: {}",
                 singletonContext.getClass().getSimpleName(), singletonContext.getApplication().toString());
    }
}
