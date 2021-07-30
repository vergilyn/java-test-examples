package com.vergilyn.examples.mockito.behavior;

import java.io.PrintStream;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 *
 * @author vergilyn
 * @since 2021-07-30
 */
public class BehaviorTest {
	public static final AtomicInteger INDEX = new AtomicInteger(0);

	/**
	 * 1. 是否会真实执行<b>1次</b> `someMethod()`？ 要看是 spy/mock。 mock不会，spy会。<br/>
	 * 2. 所以，mock的when不会调用`someMethod()`，但是spy的when会调用！<br/>
	 *
	 * <br/>
	 * <p> <b>扩展</b>
	 *   特殊情况，如果需要 mockito 测试 spring-aop。
	 *   <pre>
	 *     AdviceClass targetObject = AopTestUtils.getTargetObject(adviceClassInstance);
	 *     when(targetObject.someMethod(any())).thenReturn(ResponseDTO.success(resultThird()));
	 *   </pre>
	 *   `adviceClassInstance`如果是 mock-bean，无法触发 spring-aop。<br/>
	 *   如果是 spy-bean，`when`会调用1次`someMethod()`，然后因为`any()`抛出NPE！
	 * </p>
	 *
	 * @see null mockito-springboot-examples, `MockitoAopTests.class`
	 */
	@Test
	public void error(){
		BehaviorTest instance = Mockito.spy(BehaviorTest.class);

		// `when(spy.someMethod(..))` 会调用1次`someMethod()`，并且因为是`ArgumentMatchers.any()`导致`someMethod()`内部抛出NPE
		when(instance.someMethod(any())).thenReturn(false);

		boolean result = instance.someMethod(new ParamDto(false));
		print("result: " + result);
	}

	@Test
	public void rightMock(){
		BehaviorTest mockInstance = Mockito.mock(BehaviorTest.class);

		// mock 并不会真实调用一次方法，所以不会存在NPE的问题
		when(mockInstance.someMethod(any())).thenReturn(false);

		boolean result = mockInstance.someMethod(new ParamDto(true));
		print("result: " + result);
	}

	/**
	 * Q1. spy会调用几次`someMethod()`？<br/>
	 *   通过 breakpoint-debug，会发现`someMethod()`被调用了 3次！但是只print 1次"someMethod.."，且 INDEX = 1。<br/>
	 *   `when before`之后触发2次（第1次不打印"someMethod"，第2次打印），`instance before`之后触发1次（不打印"someMethod"）。
	 */
	@Test
	public void execute(){
		print("spy before: " + LocalTime.now());
		BehaviorTest instance = Mockito.spy(BehaviorTest.class);
		print("spy after: " + LocalTime.now()).println();

		ParamDto paramDto = new ParamDto(false);

		print("when before: " + LocalTime.now());
		when(instance.someMethod(paramDto)).thenReturn(false);
		print("when after: " + LocalTime.now()).println();


		print("instance before: " + LocalTime.now());
		boolean result = instance.someMethod(paramDto);
		print("instance after: " + LocalTime.now()).println();

		print("result: " + result);
		print("INDEX: " + INDEX.get());
	}

	@SneakyThrows
	public boolean someMethod(ParamDto param){
		// 在此 breakpoint
		print(String.format("[%d]execute `someMethod(%s)`", INDEX.incrementAndGet(), JSON.toJSONString(param)));

		// `ArgumentMatchers.any()` 会导致 NPE。
		if (param.result){
			return true;
		}

		return false;
	}

	@SneakyThrows
	private PrintStream print(String str){
		// TimeUnit.SECONDS.sleep(1);
		System.out.printf("[vergilyn][%s] >>>> %s \n", LocalTime.now(), str);

		return System.out;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ParamDto {
		private Boolean result;
	}
}
