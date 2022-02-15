package com.vergilyn.examples.jupiter.arguments;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.JsonFileSource;

public class JsonFileSourceTests {

	@ParameterizedTest
	@JsonFileSource(filepath = "classpath:json-file-source-tests.json")
	public void test(JSONObject first, JSONArray second){
		System.out.println("first >>>> " + first);
		System.out.println("second >>>> " + second);
		System.out.println();
	}

}
