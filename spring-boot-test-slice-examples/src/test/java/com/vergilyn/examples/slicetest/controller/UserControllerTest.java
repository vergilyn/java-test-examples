package com.vergilyn.examples.slicetest.controller;

import com.vergilyn.examples.slicetest.SliceTestApplication;
import com.vergilyn.examples.slicetest.controller.core.FilterBeanDefinitionRegistryPostProcessor;
import com.vergilyn.examples.slicetest.controller.core.UserOtherControllerTypeExcludeFilter;
import com.vergilyn.examples.slicetest.user.UserOtherService;
import com.vergilyn.examples.slicetest.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 默认，{@link WebMvcTest} 会注册所有的 controller，例如 {@code `com.vergilyn.examples.slicetest.controller`}下的所有 controller's：
 * <pre>
 *     - {@link UserController}
 *     - {@link UserOtherController}
 *     - ...
 * </pre>
 * <p>
 * 由于不同的 controller 可能依赖不同的 service或其它bean。
 * 这样导致，例如只想测试{@link UserController}时，还需要 mock {@link UserOtherService}。
 * 那如果存在 100个controller's，对应 120个service's（有对应 200个 dao's），显然不能手动通过 {@link MockBean}简单的注册。
 *
 * <p> 所以，提供了 {@link WebMvcTest#controllers()}。只注册指定的 controller，对应的只需要mock此controller需要的依赖。
 *
 *
 * <h3>情况 02</h3>
 * 如果存在 例如 {@link SliceTestApplication} 被 `@ComponentScan("com.vergilyn.examples.slicetest.controller")` 注解。
 * <pre>{@code
 *   @SpringBootApplication
 *   @ComponentScan("com.vergilyn.examples.slicetest.controller")
 *   public class SliceTestApplication {
 *
 *       public static void main(String[] args) {
 *           SpringApplication application = new SpringApplication(SliceTestApplication.class);
 *           application.run(args);
 *       }
 *
 *   }
 * }</pre>
 *
 * 那么，依然会通过 {@link ComponentScan} 被注册到 spring中。
 *
 * <p> Q：如何在不修改 {@link SliceTestApplication} 的情况下，排除 {@link UserOtherController} 的注册？
 * <p> 1）无法通过在 单元测试类中增加 {@link ComponentScan#excludeFilters()} 进行排除。
 * <br/> 因为，多个 {@link ComponentScan} 之间是相互独立执行的。
 *
 * <p> 2）
 *
 * <p> 3）<b>可行方案</b>，参考{@link FilterBeanDefinitionRegistryPostProcessor}。
 *
 * @author dingmaohai
 * @version v1.0
 * @since 2023/08/14 14:36
 */
@WebMvcTest(UserController.class)

// 1: 通过这种方式，可以阻止注册到spring。（会先被扫描注册到 BeanDefinitionRegistry）
// @Import(UserControllerTest.FilterBeanDefinitionRegistryPostProcessor.class)

@Import(UserOtherControllerTypeExcludeFilter.class)

// 如果存在 `@ComponentScan`，依然会将`UserOtherController`注册到spring中。
// @ComponentScan(basePackages = "com.vergilyn.examples.slicetest.controller",
//         excludeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = ExcludeUserOtherControllerTypeFilter.class)})
class UserControllerTest {
    @Autowired
    private ApplicationContext context;

    @MockBean
    private UserService userService;

    @Test
    void test() {
        assertThat(context.getBeanNamesForType(UserController.class)).isNotEmpty();
        assertThat(context.getBeanNamesForType(UserService.class)).isNotEmpty();

        assertThat(context.getBeanNamesForType(UserOtherController.class)).isEmpty();
        assertThat(context.getBeanNamesForType(UserOtherService.class)).isEmpty();
    }

}

