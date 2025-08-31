package com.concentration.proxy;

import org.aspectj.weaver.patterns.ArgsAnnotationPointcut;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxyFactory {
    // what who how
    private Object targer;

    public MyProxyFactory(Object targer){
        this.targer = targer;
    }

    public Object JdkGetProxyInstance(){
        ClassLoader loader = targer.getClass().getClassLoader();
        Class<?>[] interfaces = targer.getClass().getInterfaces();
        InvocationHandler h = (Object proxy, Method method, Object[] args) ->{
            System.out.print("搜房源 -> 分析对比 -> 联系房东 ->");
            Object invoked = method.invoke(targer, args);
            return invoked;
        };

        return Proxy.newProxyInstance(loader, interfaces, h);
    }

    public Object CGlibProxyInstance(){
        ClassLoader loader = targer.getClass().getClassLoader();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targer.getClass());
        enhancer.setClassLoader(loader);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.print("搜房源 -> 分析对比 -> 联系房东");
                Object invoked = method.invoke(targer, objects);
                return invoked;
            }
        });

        return enhancer.create();
    }
}
