# spring-boot-test-slice-examples

+ [Auto-configured Tests](https://docs.spring.io/spring-boot/docs/2.2.11.RELEASE/reference/htmlsingle/#boot-features-testing-spring-boot-applications-testing-autoconfigured-tests)

> Spring Boot’s auto-configuration system works well for applications but can sometimes be a little too much for tests.
> It often helps to load only the parts of the configuration that are required to test a **“slice”** of your application.  
> For example, you might want to test that Spring MVC controllers are mapping URLs correctly,
> and you do not want to involve database calls in those tests, or you might want to test JPA entities,  
> and you are not interested in the web layer when those tests run.
>
> The `spring-boot-test-autoconfigure` module includes a number of annotations that can be used to automatically configure such “slices”.  
> Each of them works in a similar way, providing a `@…Test`(eg. `@DataRedisTest` or `@JsonTest`) annotation
> that loads the ApplicationContext and one or more `@AutoConfigure…` annotations that can be used to customize auto-configuration settings.

**spring-boot-test slice logger:**
> [Custom Log Configuration](https://docs.spring.io/spring-boot/docs/2.2.11.RELEASE/reference/htmlsingle/#boot-features-custom-log-configuration)
> Since logging is initialized before the ApplicationContext is created,  
> it is not possible to control logging from `@PropertySources` in Spring `@Configuration` files.  
> The only way to change the logging system or disable it entirely is via System properties.
>
> When possible, we recommend that you use the `-spring` variants for your logging configuration(for example, `logback-spring.xml` rather than `logback.xml`).  
> If you use standard configuration locations, Spring cannot completely control log initialization.