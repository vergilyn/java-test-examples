package com.vergilyn.examples.mockito.sentinel;

import com.vergilyn.examples.mockito.AbstractMockitoSpringBootTests;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.springframework.boot.test.mock.mockito.SpyBean;

public class SentinelListenerTests extends AbstractMockitoSpringBootTests {

	@SpyBean
	SentinelListener listener;

	@RepeatedTest(10)
	public void test(RepetitionInfo repetitionInfo){

		listener.handler(repetitionInfo.getCurrentRepetition());
	}
}