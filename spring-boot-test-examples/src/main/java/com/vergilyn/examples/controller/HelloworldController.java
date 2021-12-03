package com.vergilyn.examples.controller;

import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/helloworld")
public class HelloworldController {

	@GetMapping("/get")
	public String get(HttpServletRequest request, String param){
		log.info("[vergilyn] HelloworldController#get, param: {}", param);

		String resp = StringUtils.isBlank(param) ? "null" : param;
		return resp + " >>>> " + LocalDateTime.now();
	}

	@PostMapping("/post")
	public Map<String, Object> post(HttpServletRequest request, @RequestBody Map<String, Object> body){
		log.info("[vergilyn] HelloworldController#post, param: {}", JSON.toJSONString(body));

		body = body == null ? Maps.newHashMap() : body;
		body.put("__time__", LocalDateTime.now());

		return body;
	}
}
