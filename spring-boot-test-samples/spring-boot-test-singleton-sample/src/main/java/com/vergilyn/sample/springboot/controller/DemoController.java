package com.vergilyn.sample.springboot.controller;

import com.vergilyn.sample.springboot.configuration.ApplicationInfo;
import com.vergilyn.sample.springboot.configuration.SingletonContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Value("${spring.application.name}")
    private String application;
    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping(value = "/get", produces = "application/json")
    public Map<String, Object> get(){
        SingletonContext singletonContext = SingletonContext.getInstance("any-string", 0);

        Map<String, Object> result = new HashMap<>();
        result.put("singleton", singletonContext.getApplication());
        result.put("actual", new ApplicationInfo(application, serverPort));

        return result;
    }
}
