# java-test-examples

- junit4
- Jupiter / JUnit 5
- testng
- spring-boot-test
- jmh(Java Micro-benchmark Harness)
- testcontainers

## junit4
- junit4: <https://junit.org/junit4/>
- junit4-docs: <https://github.com/junit-team/junit4/wiki/Maintainer-documentation>
- junit4-samples:

## Jupiter / JUnit 5
- Jupiter / JUnit 5: <https://junit.org/junit5/>
- docs: <https://junit.org/junit5/docs/current/user-guide/>
- junit5-samples: <https://github.com/junit-team/junit5-samples/tree/r5.7.0>

## testcontainers
- [testcontainers.org](https://www.testcontainers.org/)
- [testcontainers](https://github.com/testcontainers)

## awaitility
- <https://github.com/awaitility/awaitility>

junit异步测试时，现在是用`Thread.sleep(...)`或`CountDownLatch`的方式阻止测试方法退出。
`awaitility`相对可能更友好一点。
```java
Awaitility.await().atMost(1, TimeUnit.MINUTES)
        .untilAsserted(() -> Assertions.assertEquals(msgSize, msgCounter.get()));
```

see:  
- Junit单元测试多线程的问题：<https://www.cnblogs.com/yanphet/p/5774291.html>
- How to use JUnit to test asynchronous processes: <https://stackoverflow.com/questions/631598/how-to-use-junit-to-test-asynchronous-processes>