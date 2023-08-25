package com.vergilyn.examples.slicetest.controller.core;

import com.vergilyn.examples.slicetest.controller.UserOtherController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ComponentScan;

/**
 * 由于 多个{@link ComponentScan}之间彼此独立，slice-test时无法修改 已存在的{@link ComponentScan}。
 * 导致即使使用 {@code `@WebMvcTest(UserController.class)`} 也会由于存在 {@link ComponentScan} 而导致 `UserOtherController` 被注册到spring。
 *
 * <p> 期望：通过 {@link BeanDefinitionRegistry#removeBeanDefinition(String)} 移除 `UserOtherController`。
 * <p> 结果：能达到期望。
 *
 * @author dingmaohai
 * @version v1.0
 * @since 2023/08/15 17:02
 */
public class FilterBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        String[] beanDefinitionNames = registry.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);

            if (!checkCandidate(beanDefinition)) {
                registry.removeBeanDefinition(beanDefinitionName);
            }
        }
    }

    private boolean checkCandidate(BeanDefinition beanDefinition) {
        // 或者直接排除某个package 下的全部 bean。例如 UserOtherController.class.getName().startsWith("com....")
        return !UserOtherController.class.getName().equals(beanDefinition.getBeanClassName());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}