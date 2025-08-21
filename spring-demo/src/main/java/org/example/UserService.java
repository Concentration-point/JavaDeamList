package org.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class UserService implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean, DisposableBean {

    private String username;

    // 1. 首先：通过构造方法实例化
    public UserService() {
        System.out.println("1. 【构造方法】调用构造方法实例化 UserService 对象");
    }

    // 2. 然后：通过 setter 方法进行属性赋值 (DI)
    public void setUsername(String username) {
        this.username = username;
        System.out.println("2. 【属性注入】设置属性 username: " + username);
    }

    // 3. BeanNameAware 接口回调
    @Override
    public void setBeanName(String name) {
        System.out.println("3. 【BeanNameAware】回调 setBeanName: " + name);
    }

    // 4. BeanFactoryAware 接口回调
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("4. 【BeanFactoryAware】回调 setBeanFactory");
    }

    // 5. ApplicationContextAware 接口回调
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("5. 【ApplicationContextAware】回调 setApplicationContext");
    }

    // 6. @PostConstruct 注解 - BeanPostProcessor 前置处理阶段执行
    @PostConstruct
    public void postConstruct() {
        System.out.println("6. 【@PostConstruct】注解方法 postConstruct() 被调用");
    }

    // 7. InitializingBean 接口回调
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("7. 【InitializingBean】回调 afterPropertiesSet()");
    }

    // 8. 自定义的 init-method
    public void myInit() {
        System.out.println("8. 【init-method】自定义初始化方法 myInit() 被调用");
    }

    // 9. Bean 初始化完成，进入使用阶段
    public String getUserInfo() {
        return "User: " + username;
    }

    // 10. @PreDestroy 注解 - 容器关闭时执行
    @PreDestroy
    public void preDestroy() {
        System.out.println("9. 【@PreDestroy】注解方法 preDestroy() 被调用");
    }

    // 11. DisposableBean 接口回调
    @Override
    public void destroy() throws Exception {
        System.out.println("10. 【DisposableBean】回调 destroy()");
    }

    // 12. 自定义的 destroy-method
    public void myDestroy() {
        System.out.println("11. 【destroy-method】自定义销毁方法 myDestroy() 被调用");
    }
}