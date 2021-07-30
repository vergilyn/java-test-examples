package com.vergilyn.examples.testng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author vergilyn
 * @since 2021-07-30
 */
@SpringBootApplication
public class TestngSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(TestngSpringBootApplication.class);
		application.setAdditionalProfiles("h2");
		application.run(args);
	}
}
