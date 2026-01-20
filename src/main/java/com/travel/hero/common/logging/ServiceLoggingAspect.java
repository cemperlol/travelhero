package com.travel.hero.common.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class ServiceLoggingAspect {

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void serviceLayer() {}

    @Around("serviceLayer()")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        log.info(
                "->️  {}.{}() called with args: {}",
                className,
                methodName,
                Arrays.toString(joinPoint.getArgs())
        );

        try {
            Object result = joinPoint.proceed();

            long duration = System.currentTimeMillis() - start;

            log.info(
                    "<-{}.{}() finished in {} ms",
                    className,
                    methodName,
                    duration
            );

            return result;

        } catch (Throwable e) {
            long duration = System.currentTimeMillis() - start;

            log.error(
                    "<*> {}.{}() failed after {} ms",
                    className,
                    methodName,
                    duration,
                    e
            );

            throw e;
        }
    }
}
