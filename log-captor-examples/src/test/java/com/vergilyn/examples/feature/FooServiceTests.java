package com.vergilyn.examples.feature;

import com.vergilyn.examples.service.FooService;
import nl.altindag.log.LogCaptor;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <a href="https://github.com/Hakky54/log-captor#readme">Hakky54/log-captor Readme.</a>
 *
 * @author vergilyn
 * @since 2022-08-25
 */
public class FooServiceTests {

	@org.junit.jupiter.api.Test
	public void test(){
		LogCaptor logCaptor = LogCaptor.forClass(FooService.class);

		FooService fooService = new FooService();
		fooService.sayHello();

		// Get logs based on level
		assertThat(logCaptor.getInfoLogs()).containsExactly("Keyboard not responding. Press any key to continue...");
		assertThat(logCaptor.getWarnLogs()).containsExactly("Congratulations, you are pregnant!");

		// Get all logs
		assertThat(logCaptor.getLogs())
				.hasSize(2)
				.contains(
						"Keyboard not responding. Press any key to continue...",
						"Congratulations, you are pregnant!"
				);
	}
}
