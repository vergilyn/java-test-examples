package com.vergilyn.sample.springboot.configuration;

public class SingletonContext {

    private static volatile SingletonContext _instance;

    private ApplicationInfo application;

    private SingletonContext(){
        this("N/A", 0);
    }

    private SingletonContext(String name, int serverPort){
        this.application = new ApplicationInfo(name, serverPort);
    }

    public static SingletonContext getInstance(String name, int serverPort){
        if (_instance == null){
            synchronized (SingletonContext.class){
                if (_instance == null){
                    _instance = new SingletonContext(name, serverPort);
                }
            }
        }

        return _instance;
    }

    public ApplicationInfo getApplication() {
        return application;
    }
}
