package com.project.studentcrud.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    // Before method call
    @Before("execution(* com.project.studentcrud.controller.*.*(..)) || execution(* com.project.studentcrud.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("➡ Entering Method: " + joinPoint.getSignature().getName() +
                " | Arguments: " + Arrays.toString(joinPoint.getArgs()));
    }

    // After successful method return
    @AfterReturning(pointcut = "execution(* com.project.studentcrud.controller.*.*(..)) || execution(* com.project.studentcrud.service.*.*(..))",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println(" Method Completed: " + joinPoint.getSignature().getName() +
                " | Return Value: " + result);
    }

    // After exception
    @AfterThrowing(pointcut = "execution(* com.project.studentcrud.controller.*.*(..)) || execution(* com.project.studentcrud.service.*.*(..))",
            throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        System.out.println(" Exception in Method: " + joinPoint.getSignature().getName() +
                " | Exception: " + exception.getMessage());
    }

    // Around advice to measure time
    @Around("execution(* com.project.studentcrud.controller.*.*(..)) || execution(* com.project.studentcrud.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed(); // actual method execution
        long executionTime = System.currentTimeMillis() - start;

        System.out.println("⏱ Execution time for method " + joinPoint.getSignature().getName() +
                " : " + executionTime + " ms");

        return proceed;
    }
}

