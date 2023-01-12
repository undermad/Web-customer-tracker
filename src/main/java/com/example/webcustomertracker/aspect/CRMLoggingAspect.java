package com.example.webcustomertracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.example.webcustomertracker.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* com.example.webcustomertracker.services.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* com.example.webcustomertracker.services.*.*(..))")
    private void forDAOPackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow() {
    }

    @Before("forAppFlow()")
    private void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logger.info(">>> Method called: " + methodSignature.getName());

        Object[] arg = joinPoint.getArgs();

        for (int i = 0; i < arg.length; i++) {
            logger.info(">>> Arg " + i + ": " + arg[i]);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodSignature = joinPoint.getSignature().toShortString();

        logger.info(">>> @AfterReturning from method: " + methodSignature);

        logger.info(">>> Object: " + result);
    }


}
