package com.concentration.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Pointcut("execution(* com.concentration.service.impl.UserServiceImpl.*())")
    public void pointCut1(){}

    @Pointcut("@annotation(com.concentration.annotation.MyAnnotation)")
    public void pointCut2(){}
    @Around("pointCut2()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("前  记录日志");
        Object proceed = joinPoint.proceed();
        System.out.println("后  记录日志");

        return proceed;
    }
}
