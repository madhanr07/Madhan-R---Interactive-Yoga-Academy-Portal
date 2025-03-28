package com.prjgrp.artf.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.prjgrp.artf.controller..*(..))")
    public void controllerMethods() {
    }

    @Pointcut("execution(* com.prjgrp.artf.service..*(..))")
    public void serviceMethods() {
    }

    @Pointcut("execution(* com.prjgrp.artf.repository..*(..))")
    public void repositoryMethods() {
    }

    @Before("controllerMethods() || serviceMethods() || repositoryMethods()")
    public void logMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        logger.info("Entering method: {} with arguments: {}", methodName, args);
    }

    @After("controllerMethods() || serviceMethods() || repositoryMethods()")
    public void logMethodExit(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("Exiting method: {}", methodName);
    }

    @AfterReturning(pointcut = "controllerMethods() || serviceMethods() || repositoryMethods()", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("Method {} returned with value: {}", methodName, result);
    }

    @AfterThrowing(pointcut = "controllerMethods() || serviceMethods() || repositoryMethods()", throwing = "exception")
    public void logMethodException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.error("Method {} threw exception: {}", methodName, exception.getMessage());
    }
}
