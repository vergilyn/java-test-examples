package com.vergilyn.examples.jupiter.meta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class NativeDependencyInjectionJupiter {

	@BeforeEach
	public void beforeEach(TestInfo testInfo){
		System.out.printf("test-class: %s \n", testInfo.getTestClass().get().getName());

		// 未声明`@DisplayName`时，默认用`method#name()`
		System.out.printf("test-method: %s \n", testInfo.getDisplayName());
	}

	@Test
	@DisplayName("test-display-name")
	public void test01(){
		System.out.println("#test01");
	}
}
