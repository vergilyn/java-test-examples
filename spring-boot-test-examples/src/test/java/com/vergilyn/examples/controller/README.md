# compare

`MockMvc` vs `WebTestClient` vs `RandomPort`:
> <https://docs.spring.io/spring-boot/docs/2.2.11.RELEASE/reference/html/spring-boot-features.html#boot-features-testing-spring-boot-applications-testing-with-mock-environment>
>
> Testing within a `mocked environment` is usually **faster** than running with a full Servlet container. 
> However, since mocking occurs at the Spring MVC layer, 
> code that relies on lower-level Servlet container behavior cannot be directly tested with MockMvc.
> （不能直接用MockMvc测试依赖低级Servlet容器行为的代码）
> 
> For example, Spring Boot’s error handling is based on the “error page” support provided by the Servlet container. 
> This means that, whilst you can test your MVC layer throws and handles exceptions as expected, 
> you cannot directly test that a specific custom error page is rendered. 
> If you need to test these lower-level concerns, you can start a fully running server as described in the next section.
> （fully 可以通过 random-port方式启动。）