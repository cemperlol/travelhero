package com.travel.hero.common.logging;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Map;

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
        Object[] args = joinPoint.getArgs();

        log.info("-> {} {}.{}({}) - User-Agent: {}",
                request.getMethod(),
                className,
                methodName,
                args.length > 0 ? Arrays.toString(args) : "",
                request.getHeader("User-Agent")
        );

        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;

            String status = extractStatus(result);

            log.info("<- {} {}.{}() - Status: {}, Time: {} ms",
                    request.getMethod(),
                    className,
                    methodName,
                    status,
                    duration
            );

            return result;
        } catch (Throwable e) {
            long duration = System.currentTimeMillis() - start;

            log.error("<!> {} {}.{}() - Error: {}, Time: {} ms",
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

    private String extractStatus(Object result) {
        switch (result) {
            case null -> {
                return "204 NO_CONTENT";
            }
            case ResponseEntity<?> responseEntity -> {
                HttpStatusCode statusCode = responseEntity.getStatusCode();
                return statusCode.value() + " " + statusCode;
            }
            case byte[] bytes -> {
                return "200 OK (byte[])";
            }
            case Map map -> {
                return "200 OK (Map)";
            }
            default -> { }
        }

        return "200 OK (" + result.getClass().getSimpleName() + ")";
    }
}
