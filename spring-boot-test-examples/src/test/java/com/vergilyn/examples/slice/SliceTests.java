package com.vergilyn.examples.slice;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.vergilyn.examples.AbstractSpringBootTestApplicationTests;
import com.vergilyn.examples.mapper.UserMapper;
import com.vergilyn.examples.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.sql.DataSource;

/**
 * 期望：按需要 加载最小需要 的spring-bean 或者 相关configuration。
 * <p> 1. 最好都继承 {@link AbstractSpringBootTestApplicationTests} 保持不变。
 * <p> 2. 基本的 ComponentScan 最好还是继承至{@link AbstractSpringBootTestApplicationTests}
 *
 * @author vergilyn
 * @since 2022-06-10
 */
@DataRedisTest(excludeFilters = {
		@ComponentScan.Filter(classes = MybatisPlusAutoConfiguration.class)
})
public class SliceTests {
	@Autowired(required = false)
	private StringRedisTemplate stringRedisTemplate;

	@Autowired(required = false)
	private DataSource dataSource;

	@Autowired(required = false)
	private UserService userService;

	@Autowired(required = false)
	private UserMapper userMapper;

	@Test
	public void test(){
		print("stringRedisTemplate", stringRedisTemplate);
		print("dataSource", dataSource);
		print("userService", userService);
		print("userMapper", userMapper);
	}

	protected void print(String prefix, Object obj){
		System.out.printf("%s >>>> %s \n", prefix, obj == null ? "NULL" : obj);
	}
}
