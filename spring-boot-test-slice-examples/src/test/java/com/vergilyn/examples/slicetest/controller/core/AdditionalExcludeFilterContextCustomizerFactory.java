package com.vergilyn.examples.slicetest.controller.core;

import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.ContextCustomizerFactory;

import java.util.List;

/**
 *
 * {@linkplain org.springframework.context.annotation.ComponentScanAnnotationParser#parse(AnnotationAttributes, String) ComponentScanAnnotationParser#parse(AnnotationAttributes, String)}
 * 如果 `excludeFilters == empty`，根本不会创建 任何的 excludeFilter's ，
 * <b>所以通过此方式添加到 default-ExcludeFilter's 也不行。</b>
 *
 * @author dingmaohai
 * @version v1.0
 * @since 2023/08/16 11:31
 *
 * @see org.springframework.boot.test.mock.mockito.MockitoContextCustomizerFactory
 */
public class AdditionalExcludeFilterContextCustomizerFactory implements ContextCustomizerFactory {

    @Override
    public ContextCustomizer createContextCustomizer(Class<?> testClass,
                                                     List<ContextConfigurationAttributes> configAttributes) {
        return new AdditionalExcludeFilterContextCustomizer();
    }
}
