package com.vergilyn.examples.slicetest.controller.core;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.MergedContextConfiguration;

/**
 *
 * @author dingmaohai
 * @version v1.0
 * @since 2023/08/16 10:45
 *
 * @see org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer
 * @see org.springframework.boot.test.context.filter.TestTypeExcludeFilter
 */
public class AdditionalExcludeFilterContextCustomizer implements ContextCustomizer {

    @Override
    public void customizeContext(ConfigurableApplicationContext context, MergedContextConfiguration mergedConfig) {
        context.getBeanFactory().registerSingleton(UserOtherControllerTypeExcludeFilter.class.getName(), new UserOtherControllerTypeExcludeFilter());
    }
}
