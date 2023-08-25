package com.vergilyn.examples.slicetest.controller;

import com.vergilyn.examples.slicetest.entity.UserEntity;
import com.vergilyn.examples.slicetest.user.UserOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user/other")
public class UserOtherController {
    @Autowired
    private UserOtherService userOtherService;

    @GetMapping("/getById")
    public UserEntity getById(Integer id){
        return userOtherService.getById(id);
    }
}
