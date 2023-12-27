在需要的地方，重置单例模式对象。例如 通过反射设置`instance = null`，或者通过Mockito。

> 例如示例代码，还需要结合 `@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)`。  
> 需要重新通过创建 spring-context 来触发正确的构造 单例模式对象。

缺点：需要清楚的知道有哪些单例模式对象需要被重置。


思考：  
如果是依赖包中存在大量单例模式，意味着需要较多的了解其中哪些单例模式对象会对单元测试造成影响，并在需要的时候重置单例模式对象。
