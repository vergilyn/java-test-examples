package com.vergilyn.sample.springboot.context;

import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

public class ContextHolder {
    private static volatile ContextHolder INSTANCE;

    @Getter
    private ApplicationContext context;

    private ContextHolder(){
        this(null);
    }

    private ContextHolder(ApplicationContext context){
        Assert.notNull(context, "`context` must be not null");
        this.context = context;
    }

    public static ContextHolder getInstance(){
        return getInstance(null);
    }

    public static ContextHolder getInstance(ApplicationContext context){
        if (INSTANCE == null){
            synchronized (ContextHolder.class){
                if (INSTANCE == null){
                    INSTANCE = new ContextHolder(context);
                }
            }
        }

        return INSTANCE;
    }
}
