package com.baizhi.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;

@Configuration
@Aspect
public class RedisString {
    @Autowired
    private Jedis jedis;

    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        boolean b = method.isAnnotationPresent(com.baizhi.annotation.RedisString.class);
        if(b){

        }else {
            result = proceedingJoinPoint.proceed();
        }
        jedis.close();
        return result;
    }
}
