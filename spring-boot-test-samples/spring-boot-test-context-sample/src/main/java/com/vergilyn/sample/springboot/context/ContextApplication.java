package com.vergilyn.sample.springboot.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class ContextApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ContextApplication.class);

        log.info("{} 启动成功 >>>> server.port: {}",
                 context.getId(), context.getEnvironment().getProperty("server.port"));

    }
}
