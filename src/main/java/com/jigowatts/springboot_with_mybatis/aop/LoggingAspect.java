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

import lombok.extern.slf4j.Slf4j;

/**
 * LoggingAspect
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* *..*Controller.*(..))")
    public Object log(ProceedingJoinPoint jp) throws Throwable {
        log.debug("コントローラ【開始】：{}", jp.getSignature());
        try {
            Object result = jp.proceed();
            log.debug("コントローラ【正常終了】: {} 戻り値={}", jp.getSignature(), result);
            return result;
        } catch (Exception e) {
            log.error("コントローラ【異常終了】: {}", jp.getSignature());
            log.error("エラー内容：",e);
            throw e;
        }
    }

    @Before("execution(* *..*Service.*(..))")
    public void startLog(JoinPoint jp) {
        log.debug("-->サービス【開始】: ", jp.getSignature());
    }

    @After("execution(* *..*Service.*(..))")
    public void endLog(JoinPoint jp) {
        log.debug("-->サービス【終了】: ", jp.getSignature());
    }

    @AfterReturning("execution(* *..*Service.*(..))")
    public void nomalEndLog(JoinPoint jp) {
        log.debug("-->【正常】: ", jp.getSignature());
    }

    @AfterThrowing("execution(* *..*Service.*(..))")
    public void exceptionEndLog(JoinPoint jp) {
        log.debug("-->【異常】: ", jp.getSignature());
    }

}