package com.vergilyn.examples.jupiter.feature;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MultiAssertTests {

	/**
	 * 期望：可以执行 所有的assert。（非阻断）
	 */
	@Test
	public void nonBlockAssert() {
		Assertions.assertAll(() -> Assertions.assertEquals(1, 2),
		                     () -> Assertions.assertEquals("a", "b"),
		                     () -> Assertions.assertInstanceOf(List.class, new Integer(2)));
	}

	/**
	 * 阻断
	 */
	@Test
	public void block() {
		Assertions.assertEquals(1, 2);
		Assertions.assertEquals("a", "b");
		Assertions.assertInstanceOf(List.class, new Integer(2));
	}
}
