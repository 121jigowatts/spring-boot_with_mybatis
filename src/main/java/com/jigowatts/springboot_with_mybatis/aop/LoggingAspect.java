package com.jigowatts.springboot_with_mybatis.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * LoggingAspect
 */
@Aspect
@Component
public class LoggingAspect {
    @Around("execution(* *..*Controller.*(..))")
    public Object log(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("コントローラ【開始】：" + jp.getSignature());
        try {
            Object result = jp.proceed();
            System.out.println("コントローラ【正常終了】: " + jp.getSignature() + " 戻り値=" + result);
            return result;
        } catch (Exception e) {
            System.out.println("コントローラ【異常終了】: " + jp.getSignature());
            e.printStackTrace();
            throw e;
        }
    }

}