package com.baizhi.aop;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.jasper.tagplugins.jstl.core.If;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Set;

@Configuration
@Aspect
public class RedisCache {
    //注入Jedis
    @Autowired
    private Jedis jedis;

    //配置切入点
    @Around("execution(* com.baizhi.service.*.find*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws  Throwable{
        //判断注解是否存在
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        boolean b = method.isAnnotationPresent(com.baizhi.annotation.RedisCache.class);
        Object result = null;
        //存在注解
        if (b){
            StringBuilder stringBuilder = new StringBuilder();
            //获取类名
            String name = proceedingJoinPoint.getTarget().getClass().getName();
            //获取方法名
            String methodName = proceedingJoinPoint.getSignature().getName();
            //获取参数
            Object[] args = proceedingJoinPoint.getArgs();
            //拼接
            stringBuilder.append(name).append(".").append(methodName).append(":");
            for (int i = 0; i < args.length; i++) {
                stringBuilder.append(args[i]);
                if(i == args.length-1){
                    break;
                }
                stringBuilder.append(",");
            }
            String key = stringBuilder.toString();
            //System.out.println("key=="+key);
            //判断是否有key
            if(jedis.exists(key)){
                //有key
                String json = jedis.get(key);
                result = JSONObject.parse(json);

            }else {
                //不含有该key
                result = proceedingJoinPoint.proceed();
                jedis.set(key, JSONObject.toJSONString(result));
            }
            //jedis.close();
            return result;
        }else {
            //不含有注解
            result = proceedingJoinPoint.proceed();
            //jedis.close();
            return result;
        }
    }

    @After("execution(* com.baizhi.service.*.*(..)) && !execution(* com.baizhi.service.*.find*(..))")
    public void after(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getName();
        Set<String> keys = jedis.keys("*");
        keys.forEach(key -> {
            if (key.startsWith(className)){
                System.out.println("============"+key);
                jedis.del(key);
            }
        });
        jedis.close();
    }
}
