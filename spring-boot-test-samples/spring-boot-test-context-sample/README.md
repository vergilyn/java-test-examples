

## Junit5 控制测试类的执行顺序
参考：https://junit.org/junit5/docs/5.8.0/user-guide/index.html#writing-tests-test-execution-order-classes

（`@TestClassOrder` 根据javadoc说明，貌似只能用于控制`@Nested`嵌套测试类的执行顺序）

## Spring Test 复用`ApplicationContext`
`spring-test`在运行测试时会尽可能地复用已经创建的`ApplicationContext`，以提高测试的执行速度。  

根据以下条件来决定是否可以复用ApplicationContext：
> （来源 ChatGPT）：
> - 测试类的类名和类路径
> - 测试类的父类
> - 测试类上的所有Spring Boot和Spring测试注解，包括它们的属性值
> - 所有活动的Spring和Spring Boot配置文件
> - 所有在测试类上声明的@ActiveProfiles注解
> - 所有在测试类上声明的@TestPropertySource注解
> - 所有在测试类上声明的@DirtiesContext注解
> - 所有在测试类上声明的@TestConstructor注解
> - 所有在测试类上声明的@TestInstance注解

官方文档参考：[Spring(v5.3.29) Context Caching]  
（这个特性从`Spring: 5.2.0.RELEASE` 就已经支持了，甚至更早）
> Once the TestContext framework loads an `ApplicationContext` (or `WebApplicationContext`) for a test,   
> that context is cached and reused for all subsequent tests that declare the same unique context configuration within the same test suite.   
> To understand how caching works, it is important to understand what is meant by “unique” and “test suite.”
> 
> An `ApplicationContext` can be uniquely identified by the combination of configuration parameters that is used to load it.   
> Consequently, the unique combination of configuration parameters is used to generate a key under which the context is cached.   
> The TestContext framework uses the following configuration parameters to build the context cache key:
> 
> + `locations` (from `@ContextConfiguration`)
> + `classes` (from `@ContextConfiguration`)
> + `contextInitializerClasses` (from `@ContextConfiguration`)
> + `contextCustomizers` (from `ContextCustomizerFactory`) – this includes `@DynamicPropertySource` methods as well as various features from Spring Boot’s testing support such as `@MockBean` and `@SpyBean`.
> + `contextLoader` (from `@ContextConfiguration`)
> + `parent` (from `@ContextHierarchy`)
> + `activeProfiles` (from `@ActiveProfiles`)
> + `propertySourceLocations` (from `@TestPropertySource`)
> + `propertySourceProperties` (from `@TestPropertySource`)
> + `resourceBasePath` (from `@WebAppConfiguration`)


### 特别注意
只是复用`ApplicationContext`，或者重新创建。由于都是基于Junit5，所以都是在同一个JVM中。  
所以，注意`单例模式对象`造成的妨碍。

[Spring(v5.3.29) Context Caching]: https://docs.spring.io/spring-framework/docs/5.3.29/reference/html/testing.html#testcontext-ctx-management-caching

## `ContextHolder.java`
由于junit5只会运行1个JVM，所以使用`单例模式`来保留前一次的`ApplicationContext`对象，用于后续断言。 

[ContextHolder.java]: src%2Ftest%2Fjava%2Fcom%2Fvergilyn%2Fsample%2Fspringboot%2Fcontext%2FContextHolder.java

## 案例

### case-01

结论：`test11.ApplicationContext == test12.ApplicationContext`。

[ContextTest11.java]: src%2Ftest%2Fjava%2Fcom%2Fvergilyn%2Fsample%2Fspringboot%2Fcontext%2Fcase01%2FContextTest11.java
[ContextTest12.java]: src%2Ftest%2Fjava%2Fcom%2Fvergilyn%2Fsample%2Fspringboot%2Fcontext%2Fcase01%2FContextTest12.java


### case-02
虽然没有继承相同的`@SpringBootTest(...)`，但是注解内容相同。
所以，`test21.ApplicationContext == test22.ApplicationContext`。  


### case-03
配置项存在差异，`test31.ApplicationContext != test32.ApplicationContext`。

### case-04
配置项相同，但是存在`@DirtiesContext`