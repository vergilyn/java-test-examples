package com.vergilyn.examples.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.MockUtil;

import java.util.List;

public class MockUtilTests {

	/**
	 * 判断是否是 mock/spy 对象。
	 * 同样适用于 spring-boot-test的 `@MockBean` 和 `@SpyBean`
	 */
	@Test
	public void isMock(){
		List mock = Mockito.mock(List.class);

		Assertions.assertTrue(MockUtil.isMock(mock));
		Assertions.assertFalse(MockUtil.isSpy(mock));
	}
}
