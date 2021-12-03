package com.vergilyn.examples.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.vergilyn.examples.entity.UserEntity;
import com.vergilyn.examples.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	protected UserService userService;


	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestBody Map<String, Object> body){
		log.info("[vergilyn] UserController#insert >>>> body: {}", JSON.toJSONString(body));

		userService.save(body.getOrDefault("username", "DEFAULT-" + LocalDateTime.now()).toString());

		return "insert success";
	}

	@DeleteMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam String username){
		log.info("[vergilyn] UserController#delete >>>> username: {}", username);

		userService.delete(username);

		return "delete success";
	}

	@GetMapping("/get")
	public String get(HttpServletRequest request, String username){
		log.info("[vergilyn] UserController#get >>>> username: {}", username);

		List<UserEntity> list = userService.get(username);

		UserEntity entity;
		if (list == null || list.isEmpty()){
			entity = new UserEntity();
			entity.setId(0L);
			entity.setUsername("null-username");

		}else {
			entity = list.get(0);
		}

		return JSON.toJSONString(entity);
	}
}
