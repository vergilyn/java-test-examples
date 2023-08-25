package com.vergilyn.examples.slicetest.controller.core;

import com.vergilyn.examples.slicetest.controller.UserOtherController;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;

public class UserOtherControllerTypeExcludeFilter extends TypeExcludeFilter {

    @Override
    public boolean match(MetadataReader metadataReader,
                         MetadataReaderFactory metadataReaderFactory) throws IOException {
        String className = metadataReader.getClassMetadata().getClassName();
        return className.equals(UserOtherController.class.getName());
    }
}
