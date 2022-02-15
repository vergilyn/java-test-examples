package org.junit.jupiter.params.provider;

import org.apiguardian.api.API;

import java.lang.annotation.*;

import static org.apiguardian.api.API.Status.STABLE;

/**
 * json 需要是 二维数组，例如:
 * <pre>
 * [
 *  [json-object / json-array],
 *  ...
 * ]
 * </pre>
 *
 * index[n][m]: <br/>
 *   n 执行test-method 的次数，每次执行 test-method 的参数是`index[n]`；<br/>
 *   m 表示第几个参数；<br/>
 *
 * 例如`[ [{}, []] ]`, <pre>
 *
 * {@code @ParameterizedTest}  // 不能用`@Test`
 * {@code @JsonFileSource}
 * public void test(JSONObject p1, JSONArray p2){
 *     // ...
 * }
 * </pre>
 *
 * @author vergilyn
 * @since 2022-02-14
 *
 * @see CsvFileSource
 */
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@API(status = STABLE, since = "5.7")
@ArgumentsSource(JsonFileArgumentProvider.class)
public @interface JsonFileSource {

	String filepath() default "";

	String encoding() default "UTF-8";

}
