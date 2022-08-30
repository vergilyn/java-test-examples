package com.vergilyn.examples.feature;

import lombok.extern.slf4j.Slf4j;
import nl.altindag.log.LogCaptor;
import nl.altindag.log.model.LogEvent;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class FooServiceMDCTests {

	@Test
	public void test() {
		LogCaptor logCaptor = LogCaptor.forClass(FooService.class);

		new FooService().sayHelloMDC();

		List<LogEvent> logEvents = logCaptor.getLogEvents();

		assertThat(logEvents).hasSize(2);

		assertThat(logEvents.get(0).getDiagnosticContext()).
				hasSize(1)
				.extractingByKey("my-mdc-key").isEqualTo("my-mdc-value");

		assertThat(logEvents.get(1).getDiagnosticContext()).isEmpty();
	}

	@Slf4j
	public static class FooService {
		public void sayHelloMDC() {
			try {
				MDC.put("my-mdc-key", "my-mdc-value");
				// [MDC][%mdc{username:-none}] %message , MDC.values: %X{} %n
				log.info("FooService#sayHelloMDC() ...");

			} finally {
				MDC.clear();
			}

			log.info("Hello there!");
		}
	}
}
