# @TestConfiguration

+ [2.7.14, Detecting Test Configuration](https://docs.spring.io/spring-boot/docs/2.7.14/reference/htmlsingle/#features.testing.spring-boot-applications.detecting-configuration)
+ [2.7.14, Excluding Test Configuration](https://docs.spring.io/spring-boot/docs/2.7.14/reference/htmlsingle/#features.testing.spring-boot-applications.excluding-configuration)

1. 如果是 top-class 被`TestConfiguration`修饰，则不会被 auto-registry。

2. 如果是 static-nested-class 被`TestConfiguration`修饰，则会被 auto-registry。

3. 嵌套的`@TestConfiguration`类
> <https://docs.spring.io/spring-boot/docs/2.7.14/reference/htmlsingle/#features.testing.spring-boot-applications.detecting-configuration>  
> If you want to customize the primary configuration, you can use a nested @TestConfiguration class.   
> Unlike a nested @Configuration class, which would be used **instead** of your application’s primary configuration, 
> a nested @TestConfiguration class is used in **addition** to your application’s primary configuration.  
> 
> 如果您想自定义主要配置，可以使用嵌套的 @TestConfiguration 类。  
> 与嵌套的 @Configuration 类不同，后者（嵌套的 @Configuration 类）会替代应用程序的主要配置，  
> 嵌套的 @TestConfiguration 类会与应用程序的主要配置一起使用。

即：
`嵌套的 @Configuration 类` 是 **覆盖**，其中定义的 bean 或自定义配置将会覆盖主要配置中相同名称的 bean 或配置。  
`嵌套的 @TestConfiguration 类` 是**附加**到 primary-configuration 中。

TODO 2023-08-04：
通过测试代码可知，并不是说可以通过 `@Configuration` 定义一个同名的 bean。
并且，也可以通过`@Configuration` 附加新的 bean。

所以，没有理解上述说的 `覆盖` 和 `附加`的含义是什么？


### Spring Boot @TestConfiguration Example
+ <https://howtodoinjava.com/spring-boot2/testing/springboot-test-configuration/>

In spring boot, any beans configured in a top-level class annotated with @TestConfiguration will not be picked up via component scanning.   
We must explicitly register the @TestConfiguration class with the class that contains the test cases.

在Spring Boot中，如果顶层类使用`@TestConfiguration`注解进行配置的bean，将不会通过组件扫描自动加载。
我们必须**显式**地将@TestConfiguration类注册到包含测试用例的类中。

#### 2.1 Using @Import
```java
@Import(AppTestConfiguration.class)
//other annotations
public class AppTests {
	// ......
}
```

#### 2.2 Using Static Nested @TestConfiguration Class
We can define the test configurations in **nested classes inside the test class** 
The nested class can be annotated with `@Configuration` or `@TestConfiguration` annotations.  

If the class annotated with `@TestConfiguration` or `@Configuration` is a **static nested class within the test class**, 
it would be **registered automatically**. 
  - In the case of nested @Configuration class, the given configuration would be used **“instead of”** the application’s primary configuration. 
  - A nested @TestConfiguration class is used **“in addition to”** the application’s primary configuration.

```java
public class AppTests {
    @Autowired
    DataSource datasource;

    @TestConfiguration
    static class AppTestConfiguration {

        @Bean
        DataSource inMemoryDataSource(){
            return new DataSouce();
        }
    }
}
```

#### 3. Conclusion
The best thing is that these test configurations are not automatically part of the application’s primary configuration. 
These are available only on-demand using either `@Import` annotation or `static nested classes`.
（最好的是，这些测试配置不会自动成为应用程序的主配置的一部分。它们只能在需要时使用`@Import`注解或 `静态嵌套类` 来引入。 ）

Beware of the difference between `@Configutation` and `@TestConfiguration` annotations in nested classes 
as the first excludes the primary configuration while the latter is used to create additional test configuration in addition to the primary configuration.
（请注意嵌套类中@Configuration和@TestConfiguration注解之间的区别，前者会排除主配置，而后者用于在主配置之外创建额外的测试配置。）