# 解决方式一

避免使用单例模式，改成依赖注入的方式。  
（查到的大部分文档都推荐这么做，貌似spring中确实也不存在 单例模式的代码。  
或者，单例模式 的代码逻辑全部是无状态的。）

参考：  
- [Unit Tests and Singletons](https://wissel.net/blog/2020/01/unit-tests-and-singletons.html)：需要调整源代码结构。改成类似 依赖注入单例bean，然后单元测试时重新赋值。

缺点：如果是依赖包中的单例模式，无法通过此方式解决。