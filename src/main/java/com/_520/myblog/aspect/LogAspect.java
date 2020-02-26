package com._520.myblog.aspect;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com._520.myblog.controller.*.*(..))")
    public void log(){}

    @Before(value = "log()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String uri = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(uri, ip, method, args);

        log.info("{}", requestLog);
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void afterReturn(Object result){
        log.info("返回的页面：{}", result);
    }


    @AllArgsConstructor
    @ToString
    private static class RequestLog{
        private final String url;
        private final String ip;
        private final String reqMethod;
        private final Object[] args;
    }
}
