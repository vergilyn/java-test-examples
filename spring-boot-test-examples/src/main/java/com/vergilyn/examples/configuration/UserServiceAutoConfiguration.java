package com.vergilyn.examples.configuration;

import com.vergilyn.examples.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceAutoConfiguration {

    @Bean(UserService.BEAN_NAME)
    public UserService userService(){
        return new UserService(UserService.BEAN_NAME);
    }
}
