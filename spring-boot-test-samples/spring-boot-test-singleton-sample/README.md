# spring-boot-test-singleton-sample

测试`单例模式对象` 在 `@SpringBootTest` 中带来的阻碍。

结论：`单例模式`不利于 单元/集成测试，建议改成 单实例注入的方式。

## 复现(Reproduce)
参考：[reproduce](src%2Ftest%2Fjava%2Fcom%2Fvergilyn%2Fsample%2Fspringboot%2Freproduce)


说明：
~~`spring-boot: 2.2`中，多个class都被 @SpringBootTest 注解时，会启动多个 spring-boot上下文 （所以，如果端口一样，会提示端口占用）~~
（2023-12-26 更正：junit5只会运行在 1个JVM中，所以 spring-boot-2.2 也会出现单例带来的问题。）

`spring-boot: 2.7`中（貌似是从 2.3+开始），**貌似**只会运行 1个 spring上下文，导致想测试通过`x.xx.enable=true/false`控制自动装配不同的bean's时不满足期望。
 
所以，在 reproduce 的代码，如果通过 IDEA 的 `Run Tests in 'com.vergilyn.sample.springboot.reproduce'`，默认得到的结果是`SingletonTest1`的结果。

### 实际场景示例
 - [nacos-config-spring-boot-0.2.12, NacosConfigApplicationContextInitializer]
 - [nacos-config-spring-boot-0.2.12, NacosConfigLoaderFactory]

```java
package com.alibaba.boot.nacos.config.autoconfigure;

public class NacosConfigApplicationContextInitializer
		implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        singleton.setApplicationContext(context);
        environment = context.getEnvironment();
        nacosConfigProperties = NacosConfigPropertiesUtils
                .buildNacosConfigProperties(environment);
        
        final NacosConfigLoader configLoader = NacosConfigLoaderFactory.getSingleton(
                nacosConfigProperties, environment, builder);
                
        // 省略其它代码...
        configLoader.loadConfig();
    }
}
```

```java
package com.alibaba.boot.nacos.config.util;

public class NacosConfigLoaderFactory {

    private static volatile NacosConfigLoader nacosConfigLoader;

    public static NacosConfigLoader getSingleton(Function<Properties, ConfigService> builder) {
        if (nacosConfigLoader == null) {
            synchronized (NacosConfigLoaderFactory.class) {
                if (nacosConfigLoader == null) {
                    nacosConfigLoader = new NacosConfigLoader(builder);
                }
            }
        }
        return nacosConfigLoader;
    }
}
```

例如，根据`a.b.enabled=true`动态加载需要的nacos配置文件：
- `actuator.enabled=true`， 则`nacos.config.ext-config[0].data-id=actuator.properties`
- `dubbo.enabled=true`， 则`nacos.config.ext-config[1].data-id=dubbo.properties`

但是，通过`@SpringBootTest`（spring-boot 2.3+）只会启动 **1个JVM**，导致上述


[nacos-config-spring-boot-0.2.12, NacosConfigApplicationContextInitializer]: https://github.com/nacos-group/nacos-spring-boot-project/blob/0.2.12/nacos-config-spring-boot-autoconfigure/src/main/java/com/alibaba/boot/nacos/config/autoconfigure/NacosConfigApplicationContextInitializer.java#L73
[nacos-config-spring-boot-0.2.12, NacosConfigLoaderFactory]: https://github.com/nacos-group/nacos-spring-boot-project/blob/master/nacos-config-spring-boot-autoconfigure/src/main/java/com/alibaba/boot/nacos/config/util/NacosConfigLoaderFactory.java#L34


## 如何解决单例模式（扩展到：静态状态）和单元测试的互相妨碍
Junit在单元测试时，只会运行1个JVM实例。所以，导致 `单例对象` 或 `静态变量` 会干扰单元测试。

1. 调整源代码结构，改成 依赖注入（或组合）单例对象
缺点：  
需要改源代码，如果是依赖的第3方jar，无法通过这种方式来满足单元测试。
或者需要知道哪些`单例对象`需要被测试，通过Mockito或者其它方式在单元测试前重置。


2. 需要的单元测试类，使用不同的ClassLoader

- [Unit Tests and Singletons](https://wissel.net/blog/2020/01/unit-tests-and-singletons.html)：需要调整源代码结构。改成类似 依赖注入单例bean，然后单元测试时重新赋值。
- [Mocking a Singleton With Mockito](https://www.baeldung.com/java-mockito-singleton): 同上。
- [JUnit Test for Singletons/Static Variables](https://ahlamnote.blogspot.com/2017/07/junit-test-for-singletonsstatic.html)：每个单元测试类，使用不同的ClassLoader、