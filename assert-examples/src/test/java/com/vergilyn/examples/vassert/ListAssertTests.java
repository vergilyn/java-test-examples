package com.vergilyn.examples.vassert;

import com.google.common.collect.Lists;
import lombok.Data;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class ListAssertTests {

	@Test
	public void contains(){
		// junit4
//		Assert.assertThat(userGroupMapPair.getCustomizedMap().keySet(),
//				hasItems(id(1), id(2)));

		List<Integer> expected = Lists.newArrayList(1000, 3000, 2000);
		List<Integer> actual = Lists.newArrayList(1000, 3000, 2000);
		List<Integer> expectedDiffOrder = Lists.newArrayList(1000, 2000, 3000);


		// junit5, order & equal-each-element
		Assertions.assertIterableEquals(expected, actual);

		// hamcrest
		MatcherAssert.assertThat(actual, IsIterableContainingInOrder.contains(expected.toArray()));
		MatcherAssert.assertThat(actual, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedDiffOrder.toArray()));

		// assertj
		assertThat(actual)
				.containsExactlyElementsOf(expectedDiffOrder);  // the same order
		assertThat(actual)
				.containsExactlyInAnyOrderElementsOf(expectedDiffOrder);
	}

	/**
	 * 期望：A集合完全匹配B集合，集合元素是 `自定义类型`，且<b>未重写</b>{@linkplain Object#equals(Object)} 和 {@linkplain Object#hashCode()}
	 */
	@Test
	void testObjectContains(){
		List<Person> expected = Lists.newArrayList(
				new Person(1, "A"),
				new Person(2, "B")
		);

		List<Person> actual = Lists.newArrayList(
				new Person(2, "B2"),
				new Person(1, "A1")
		);

		assertThat(actual)
				.usingElementComparator((o1, o2) -> {
					boolean isEquals = Objects.equals(o1.id, o2.id) && (o2.name.contains(o1.name) || o1.name.contains(o2.name));
					return isEquals ? 0 : -1;
				})
				// `isEqualTo` 顺序需要一致
				// .isEqualTo(expected)
				.containsExactlyInAnyOrderElementsOf(expected)
		;
	}

	@Data
	private static class Person {

		private Integer id;

		private String name;

		public Person(Integer id, String name) {
			this.id = id;
			this.name = name;
		}
	}
}
