package com.vergilyn.sample.springboot;

import com.vergilyn.sample.springboot.configuration.SingletonContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class SingletonApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SingletonApplication.class);

        log.info("{} 启动成功 >>>> server.port: {}, singleton: {}",
                 context.getId(), context.getEnvironment().getProperty("server.port"),
                 SingletonContext.getInstance("any-string", 0).toString());

    }
}
