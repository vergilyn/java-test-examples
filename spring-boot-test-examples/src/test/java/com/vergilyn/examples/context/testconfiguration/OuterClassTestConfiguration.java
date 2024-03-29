package com.vergilyn.examples.context.testconfiguration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
// 取消注释后，才可以自动加载和配置并被`@TestConfiguration`修饰的的inner-class。
@org.springframework.context.annotation.ComponentScan
public class OuterClassTestConfiguration {

	@Bean(name = "outerClass")
	public String outerClass(){
		return OuterClassTestConfiguration.class.getName();
	}
}
