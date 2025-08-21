package org.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof UserService) {
            System.out.println(">> 【BeanPostProcessor】postProcessBeforeInitialization - 在初始化之前，对 bean: " + beanName + " 进行处理");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof UserService) {
            System.out.println(">> 【BeanPostProcessor】postProcessAfterInitialization - 在初始化之后，对 bean: " + beanName + " 进行处理");
            // 通常在这里会进行AOP代理等操作
        }
        return bean;
    }
}