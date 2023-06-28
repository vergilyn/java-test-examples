package com.vergilyn.examples.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Map;

public class SingletonObjectMockTest {

	@Test
	void test(){
		try (MockedStatic<Singleton> mockStatic = Mockito.mockStatic(Singleton.class)) {

			mockStatic.when(Singleton::getInstance).thenReturn(new Singleton(2));

			Singleton instance = Singleton.getInstance();
			Assertions.assertEquals(2, instance.getIndex());
		}
	}


	@Test
	void mockSingletonObject(){

	}


	static class Singleton {
		private static final Singleton INSTANCE = new Singleton(1);

		private Map<String, String> map;

		private Integer index;

		public Singleton(Integer index) {
			this.index = index;
		}

		public static Singleton getInstance(){
			return INSTANCE;
		}

		public Integer getIndex() {
			return index;
		}

		public String getByMap(String key){
			return map.get(key);
		}
	}
}
