package com.icu.mybatis.aop;

import com.icu.mybatis.annotation.RecordLog;
import com.icu.mybatis.enums.OperateLogType;
import com.icu.mybatis.mapper.OperateLogMapper;
import com.icu.mybatis.pojo.OperateLog;
import com.icu.mybatis.utils.ThreadLocalHelper;
import com.icu.mybatis.vo.login.LoginResultVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperateLogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.icu.mybatis.annotation.RecordLog)")
    public Object operateLog(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName(); // 获取目标类名
        Signature signature = joinPoint.getSignature(); // 获取目标方法签名
        String methodName = signature.getName(); // 获取目标方法名
        Object[] args = joinPoint.getArgs(); // 获取目标方法运行参数

        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature)signature;
        // 获取方法对象
        Method method = methodSignature.getMethod();
        // 获取方法上面的 RecordLog 注解
        RecordLog operateLog = method.getAnnotation(RecordLog.class);
        // 获取注解上的 value 属性
        OperateLogType operateLogType = operateLog.value();

        // 记录方法执行时间
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed(); // 执行原始方法，获取返回值（环绕通知）
        long costTime = System.currentTimeMillis() - start;

        // 从ThreadLocal中获取登录用户信息
        LoginResultVo loginResultVo = ThreadLocalHelper.get();

        OperateLog operateLogPojo = new OperateLog();
        operateLogPojo.setOperateId(loginResultVo.getId());
        operateLogPojo.setOperateTime(LocalDateTime.now());
        operateLogPojo.setClassName(className);
        operateLogPojo.setMethodName(methodName);
        operateLogPojo.setMethodArgs(Arrays.toString(args));
        operateLogPojo.setMethodResult(result.toString());
        operateLogPojo.setCostTime((int)costTime);
        operateLogPojo.setOperateType(operateLogType.getValue());

        log.info("操作日志：{}", operateLogPojo);

        operateLogMapper.save(operateLogPojo);

        return result;
    }

}
