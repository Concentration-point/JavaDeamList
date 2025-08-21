1. 实例化 (Instantiation)

做什么：Spring通过BeanDefinition找到对应的类，使用反射调用其构造方法（默认是无参构造，也可能是指定的有参构造）来创建对象实例。此时只是一个空的Java对象，属性都是默认值。

好比：工厂根据图纸(BeanDefinition) new出来一个产品毛坯。

2. 属性赋值 (Population)

做什么：Spring为刚刚创建的对象注入属性（@Autowired, @Value, <property>标签等）。

好比：给产品毛坯安装上各个零部件。

3. Aware接口回调 (Aware Interface Injection)

做什么：如果Bean实现了各种Aware接口，Spring会回调相应的方法，将一些框架级对象（如容器、Bean名等）“意识”到Bean中。

常见接口：

BeanNameAware：设置Bean的ID/名字。

BeanFactoryAware：注入当前BeanFactory的引用。

ApplicationContextAware：注入当前ApplicationContext的引用（最重要，因为ApplicationContext功能更多）。

4. BeanPostProcessor 前置处理 (Before Initialization)

做什么：这是Spring提供的一个极其强大的扩展机制。所有实现了BeanPostProcessor接口的类，它们的postProcessBeforeInitialization()方法会在这个阶段被调用。可以对Bean进行包装或修改。

关键应用：@PostConstruct注解的处理就是在此阶段执行的！ 由CommonAnnotationBeanPostProcessor这个后处理器负责。

5. 初始化 (Initialization)

做什么：执行用户自定义的初始化方法。

如果Bean实现了InitializingBean接口，会调用它的afterPropertiesSet()方法。

如果Bean在定义时指定了init-method（如@Bean(initMethod = "myInit")），则会调用这个方法。

6. BeanPostProcessor 后置处理 (After Initialization)

做什么：所有BeanPostProcessor的postProcessAfterInitialization()方法被调用。这是Bean生命周期的最后一个扩展点。

关键应用：AOP（面向切面编程）就是在此阶段发生的！ 由AnnotationAwareAspectJAutoProxyCreator这个后处理器负责。如果发现有Bean需要被切面增强，它会在这里创建一个代理对象（Proxy）并返回这个代理对象，替换掉原来的目标Bean。这就是为什么我们注入的看似是普通对象，却能拥有事务、日志等增强功能的原因。

7. 使用 (In Use)

做什么：此时Bean已经是一个完整的、位于Spring容器中的“合格品”了，会被放入单例池（Singleton Cache）中。当应用程序需要时，Spring会将其注入并使用。

8. 销毁 (Destruction)

触发时机：当Spring容器被关闭时（如调用applicationContext.close()）。

做什么：

如果Bean实现了DisposableBean接口，会调用它的destroy()方法。

如果Bean定义了destroy-method（如@Bean(destroyMethod = "myDestroy")），则会调用这个方法。

