package com.vergilyn.examples.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FooService {

	public void sayHello() {
		log.info("Keyboard not responding. Press any key to continue...");
		log.warn("Congratulations, you are pregnant!");
	}

}
