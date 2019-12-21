package com.jigowatts.springboot_with_mybatis.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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

    @Before("execution(* *..*Service.*(..))")
    public void startLog(JoinPoint jp) {
        System.out.println("-->サービス【開始】: " + jp.getSignature());
    }

    @After("execution(* *..*Service.*(..))")
    public void endLog(JoinPoint jp) {
        System.out.println("-->サービス【終了】: " + jp.getSignature());
    }

    @AfterReturning("execution(* *..*Service.*(..))")
    public void nomalEndLog(JoinPoint jp) {
        System.out.println("--->【正常】: " + jp.getSignature());
    }

    @AfterThrowing("execution(* *..*Service.*(..))")
    public void exceptionEndLog(JoinPoint jp) {
        System.out.println("--->【異常】: " + jp.getSignature());
    }

}