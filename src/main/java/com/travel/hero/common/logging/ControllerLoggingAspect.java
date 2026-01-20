package com.travel.hero.common.logging;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class ControllerLoggingAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void logControllerMethods() { }

    @Around("logControllerMethods()")
    public Object logControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        HttpServletRequest request = (
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        log.info(
                "->{} {}.{}() called with args: {}",
                request.getMethod(),
                className,
                methodName,
                request.getHeader("User-Agent")
        );

        try {
            Object result = joinPoint.proceed();

            long duration = System.currentTimeMillis() - start;
            ResponseEntity<?> response = (ResponseEntity<?>) result;

            log.info(
                    "<-{} {}.{}() — Status: {}, Time: {}",
                    request.getMethod(),
                    className,
                    methodName,
                    response.getStatusCode(),
                    duration
            );

            return result;
        } catch (Throwable e) {
            long duration = System.currentTimeMillis() - start;

            log.info(
                    "<*>{} {}.{}() — Status: {}, Time: {}",
                    request.getMethod(),
                    className,
                    methodName,
                    e.getMessage(),
                    duration,
                    e
            );

            throw e;
        }
    }
}
