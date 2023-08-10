package com.vergilyn.examples.slicetest.controller;

import com.vergilyn.examples.slicetest.entity.UserEntity;
import com.vergilyn.examples.slicetest.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getById")
    public UserEntity getById(Integer id){
        return userService.getById(id);
    }
}
