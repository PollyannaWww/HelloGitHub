package com.baizhi.aop;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.annotation.RedisCache;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;

@Configuration
@Aspect
public class RedisCacheHash {
    @Autowired
    private Jedis jedis;

    @Around("execution(* com.baizhi.service.*.find*(..))")
    public Object RedisCache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        boolean b = method.isAnnotationPresent(com.baizhi.annotation.RedisCacheHash.class);
        Object result = null;
//        查看方法是否有注解
        if(b){
            StringBuilder stringBuilder = new StringBuilder();
            //获取类名
            String className = proceedingJoinPoint.getTarget().getClass().getName();
            //获取方法名
            String methodName = proceedingJoinPoint.getSignature().getName();
            //获取参数
            Object[] args = proceedingJoinPoint.getArgs();
            //拼接
            stringBuilder.append(methodName).append(":");
            for (int i = 0; i < args.length; i++) {
                stringBuilder.append(args[i]);
                if(i == args.length-1){
                    break;
                }
                stringBuilder.append(",");
            }
            String key = stringBuilder.toString();
//            查看是否有key
            if(jedis.hexists(className,key)){
                //有key
                String json = jedis.hget(className, key);
                result = JSONObject.parse(json);
            }else {
                //没有key
                result = proceedingJoinPoint.proceed();
                jedis.hset(className,key,JSONObject.toJSONString(result));
            }
        }else {
            //不存在注解
            result = proceedingJoinPoint.proceed();
        }
        jedis.close();
        return result;
    }

    @After("execution(* com.baizhi.service.*.*(..)) && !execution(* com.baizhi.service.*.find*(..))")
    public void after(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getName();
        jedis.del(className);
        jedis.close();
    }
}
