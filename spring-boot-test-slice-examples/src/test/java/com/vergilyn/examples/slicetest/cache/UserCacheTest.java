package com.vergilyn.examples.slicetest.cache;

import com.vergilyn.examples.slicetest.CustomFirstSliceTest;
import com.vergilyn.examples.slicetest.controller.UserController;
import com.vergilyn.examples.slicetest.mapper.UserMapper;
import com.vergilyn.examples.slicetest.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.data.redis.AutoConfigureDataRedis;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 只测试 redis 相关。不期望自动配置 database、controller等。
 *
 * <pre>
 *   1. {@link DataRedisTest} 只会自动配置
 *      1.1) {@link AutoConfigureCache}
 *        - {@link org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration}
 *
 *      1.2) {@link AutoConfigureDataRedis}
 *        - {@link org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration}
 *        - {@link org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration}
 *        - {@link org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration}
 *
 *      可以加单理解成自动配置了 {@link org.springframework.data.redis.core.RedisTemplate}
 *
 *   2. 由于未自动配置 {@link UserCache}，所以可以通过以下方式自动配置
 *     2.1) {@link Import}，例如 {@code @Import(UserCache.class)}
 *     2.2) {@link ComponentScan}，例如 {@code @ComponentScan(basePackages = {"com.vergilyn.examples.slicetest.cache"})}
 *
 *   3. 可以通过{@link TestPropertySource}重写或添加 属性。
 * </pre>
 *
 * @author dingmaohai
 * @version v1.0
 * @since 2023/08/14 08:35
 *
 * @see org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
 * @see org.springframework.boot.test.context.SpringBootTest
 */
@CustomFirstSliceTest
// @DataRedisTest
// @Import(UserCache.class)
@ComponentScan(basePackages = {
        "com.vergilyn.examples.slicetest.cache"
})
@TestPropertySource(properties = {
        "vergilyn.add.env.key-01=value-01"
})
class UserCacheTest {
    @Autowired(required = false)
    UserCache userCache;
    @Autowired
    ApplicationContext context;

    @Test
    void testCacheSlice(){
        assertThat(context.getBeanNamesForType(UserController.class)).isEmpty();
        assertThat(context.getBeanNamesForType(UserService.class)).isEmpty();
        assertThat(context.getBeanNamesForType(UserMapper.class)).isEmpty();

        assertThat(context.getBeanNamesForType(UserCache.class)).isNotEmpty();
        assertThat(context.getEnvironment().getProperty("vergilyn.add.env.key-01")).isEqualTo("value-01");
    }
}