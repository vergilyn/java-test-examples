package com.vergilyn.examples.feature;

import com.vergilyn.examples.service.FooService;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <a href="https://github.com/Hakky54/log-captor#readme">Hakky54/log-captor Readme.</a>
 *
 * @author vergilyn
 * @since 2022-08-25
 */
public class FooServiceShouldTests {

	private static LogCaptor logCaptor;

	private static final String EXPECTED_INFO_MESSAGE = "Keyboard not responding. Press any key to continue...";

	private static final String EXPECTED_WARN_MESSAGE = "Congratulations, you are pregnant!";

	@BeforeAll
	public static void setupLogCaptor() {
		logCaptor = LogCaptor.forClass(FooService.class);
	}

	@AfterEach
	public void clearLogs() {
		logCaptor.clearLogs();
	}

	@AfterAll
	public static void tearDown() {
		logCaptor.close();
	}

	@Test
	public void logInfoAndWarnMessagesAndGetWithEnum() {
		FooService service = new FooService();
		service.sayHello();

		assertThat(logCaptor.getInfoLogs()).containsExactly(EXPECTED_INFO_MESSAGE);
		assertThat(logCaptor.getWarnLogs()).containsExactly(EXPECTED_WARN_MESSAGE);

		assertThat(logCaptor.getLogs()).hasSize(2);
	}

	@Test
	public void logInfoAndWarnMessagesAndGetWithString() {
		FooService service = new FooService();
		service.sayHello();

		assertThat(logCaptor.getInfoLogs()).containsExactly(EXPECTED_INFO_MESSAGE);
		assertThat(logCaptor.getWarnLogs()).containsExactly(EXPECTED_WARN_MESSAGE);

		assertThat(logCaptor.getLogs()).hasSize(2);
	}

}
