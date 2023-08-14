package com.vergilyn.examples.slicetest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.data.redis.AutoConfigureDataRedis;
import org.springframework.boot.test.autoconfigure.filter.StandardAnnotationCustomizableTypeExcludeFilter;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.*;

/**
 * slice-test的核心思路：
 * <pre>
 *   1) {@link BootstrapWith} 和 {@link ExtendWith} 都可以简单的理解成使用 spring 和JUnit 5进行测试。
 *     类似：{@link SpringBootTest}
 *
 *   2) 禁止自动配置 {@code `@OverrideAutoConfiguration(enabled = false)`}
 *
 *   3) 通过 {@link ImportAutoConfiguration} 或 `@AutoConfigureXxx` 导入期望测试的自动配置相关的类
 *
 *   4) 通过{@link TypeExcludeFilters}更精确的控制加载的类型。
 * </pre>
 *
 * <h3>1. {@code BootstrapWith}</h3>
 * 例如，基本`spring-boot-test-autoconfigure-2.7.14.jar`中所有的 `XxxTestContextBootstrapper`都类似，
 * 都是继承 {@link SpringBootTestContextBootstrapper}，并重写了 {@link SpringBootTestContextBootstrapper#getProperties(Class)}。
 * <p> 简单理解：
 * 只是将 `#getProperties()` 返回的 key-value 添加到 `environment`。
 * 所以可以直接使用 {@link SpringBootTestContextBootstrapper}
 *
 * <h3>2. {@code ExtendWith}</h3>
 * 这个注解指定了使用{@link SpringExtension}作为`JUnit 5`的扩展。
 * {@link SpringExtension}是Spring对`JUnit 5`的扩展，它提供了与Spring相关的功能，例如自动注入、事务管理等。
 * 通过使用{@link SpringExtension}，可以在Slice Test中使用Spring的功能。
 *
 * <pre>扩展参考：{@link SpringBootTest}
 * {@code
 *   @BootstrapWith(SpringBootTestContextBootstrapper.class)
 *   @ExtendWith(SpringExtension.class)
 *   public @interface SpringBootTest {
 *   }
 * }
 * </pre>
 *
 * <h3>3. {@code OverrideAutoConfiguration}</h3>
 * 用途：<b>禁用Spring Boot的自动配置</b>。
 * <p> 在Slice Test中，有时候我们可能需要手动配置或替换特定的Bean，或者避免加载不需要的自动配置。
 *   通过设置`enabled = false`，可以禁用自动配置，以便手动控制配置。
 * <blockquote>一般都是与 `@ImportAutoConfiguration`组合使用。</blockquote>
 *
 * <h3>4. （重点）{@code TypeExcludeFilters}</h3>
 * 用途：<b>{@link TypeExcludeFilters}用于排除不需要的类型，以确保只加载和测试特定切片所需的类型。</b>
 * <pre>
 *     例如排除与 {@code @CustomFirstSliceTest}注解不相关的类型。
 *
 *     虽然通过 {@code `@OverrideAutoConfiguration(enabled = false)`}禁用了spring-boot的自动配置。
 *     但例如 {@link Controller}、{@link Service}、{@link Repository}等还是会被加载到 spring。
 *     所以，需要通过{@link TypeExcludeFilters}进一步精确排除不需要的类型。
 * </pre>
 *
 * <h3>5. {@code AutoConfigureXxx}</h3>
 * 自动配置与 slice-test相关的配置。
 * <p> 可以组合和复用
 *
 * <h3>6. {@code ImportAutoConfiguration}</h3>
 * 用于导入其他需要的自动配置类，以满足更复杂的配置需求
 *
 * @author dingmaohai
 * @version v1.0
 * @since 2023/08/14 09:33
 *
 * @see org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
// @BootstrapWith(DataRedisTestContextBootstrapper.class)
@BootstrapWith(SpringBootTestContextBootstrapper.class)
@ExtendWith(SpringExtension.class)
@OverrideAutoConfiguration(enabled = false)
// @TypeExcludeFilters(DataRedisTypeExcludeFilter.class)
@TypeExcludeFilters(CustomSliceTestTypeExcludeFilter.class)
@AutoConfigureCache
@AutoConfigureDataRedis
@ImportAutoConfiguration
public @interface CustomFirstSliceTest {
}


/**
 * 核心：进一步保留与 slice-test 相关的测试类。避免 {@link Controller}、{@link Service}、{@link Repository}等还是会被自动加载到 spring。
 */
class CustomSliceTestTypeExcludeFilter extends StandardAnnotationCustomizableTypeExcludeFilter<CustomFirstSliceTest> {

    protected CustomSliceTestTypeExcludeFilter(Class<?> testClass) {
        super(testClass);
    }
}