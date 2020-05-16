package com.learning.springhibernateudemy.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppFlowLoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.learning.springhibernateudemy.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* com.learning.springhibernateudemy.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("execution(* com.learning.springhibernateudemy.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
    private void forAppFlow() {
    }

    @Before("forAppFlow()")
    private void before(JoinPoint joinPoint) {
        logger.info("\n=========>> in @Before calling method : " + joinPoint.getSignature().toShortString());

        for (Object arg : joinPoint.getArgs()) {
            logger.info("\n=========>> argument : " + arg + "[" + arg.getClass().getName() + "]");
        }
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    private void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("\n=========>> in @AfterReturning calling method : " + joinPoint.getSignature().toShortString());
        logger.info("\n=========>> result : " + result + "[" + result.getClass().getName() + "]");
    }
}