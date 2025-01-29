package com.icu.mybatis.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect // 标识这是一个切面类 AOP类
@Component
public class RecordTimeAspect {
    // 这个aop方法针对 com.icu.mybatis.services.impl 包下面所有类的所有方法（方法形参也是任意形式的）
    @Around("execution(* com.icu.mybatis.services.impl.EmployeeServiceImpl.deleteEmployee(..))||" +
            "execution(* com.icu.mybatis.services.impl.EmployeeServiceImpl.updateEmployee(..))") // 使用逻辑运算符匹配多个连接点
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        // 获取系统当前运行时间
        long startTime = System.currentTimeMillis();

        // 执行原始方式，并获取返回值
        Object result = pjp.proceed();

        long endTime = System.currentTimeMillis();
        log.info("方法[{}] 运行时间：{}ms", pjp.getSignature().toString(), (endTime - startTime));
        return result;
    }

    // 使用 @annotation 切入点表达式
    // 匹配标记有 @LogAnnotation 注解的连接点
    @Around("@annotation(com.icu.mybatis.annotations.LogAnnotation)")
    public Object recordTimeByAnnotation(ProceedingJoinPoint pjp) throws Throwable {
        // 获取系统当前运行时间
        long startTime = System.currentTimeMillis();

        // 执行原始方式，并获取返回值
        Object result = pjp.proceed();

        long endTime = System.currentTimeMillis();
        log.info("方法[{}] 运行时间：{}ms [基于 @annotation 注解匹配]", pjp.getSignature().toString(), (endTime - startTime));
        return result;
    }
}
