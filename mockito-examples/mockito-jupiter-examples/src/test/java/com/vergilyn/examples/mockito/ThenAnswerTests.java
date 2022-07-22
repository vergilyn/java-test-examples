package com.vergilyn.examples.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.atomic.AtomicInteger;

public class ThenAnswerTests {
	private static final AtomicInteger index = new AtomicInteger(0);

	/**
	 * `#thenAnswer()` 如果是 spy，是在调用方法前，还是调用方法后？
	 *
	 * <p> 通过debug可知，{@link #method()} 确实被调用了 4次，为什么只打印了1次？
	 */
	@Test
	public void test(){
		ThenAnswerTests spy = Mockito.spy(ThenAnswerTests.class);

		// 1. 第1次调用`method` 是因为 when。
		// 2. 如果调用`invocation.callRealMethod()`，那么应该是在“调用方法前” 调用`thenAnswer()`
		Mockito.when(spy.method()).thenAnswer(invocation -> {
			print("thenAnswer");

			return invocation.callRealMethod();
		});

		for (int i = 0; i < 4; i++) {
			spy.method();
		}
	}

	public int method(){
		print("spy-method");
		return 0;
	}

	private void print(String mark){
		System.out.printf("[vergilyn][%02d] >>>> %s\n", index.getAndIncrement(), mark);
	}
}
