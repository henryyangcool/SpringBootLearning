package com.example.springbootlearning.Log;

import com.example.springbootlearning.service.ApiandIPService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private ApiandIPService apiandIPService;

    @Pointcut("execution(public * com.develop.Controller.*.*(..))")
    public void log(){}

    @Around("log()")
//    ProceedingJoinPoint 取得當前方法的資料
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String ip = "";
        String method = "";
        String uri = "";
        String url = "";

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            ip = apiandIPService.getIpAndAPI(request);
            method = request.getMethod();
            uri = request.getRequestURI();
            url = request.getRequestURL().toString();
        }
        long startTime = System.currentTimeMillis();
        LogAspectData log = new LogAspectData();
        Object result = joinPoint.proceed();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        String methodName = methodSignature.getMethod().getDeclaringClass().getName() + "."
                + methodSignature.getMethod().getName();
        long endTime = System.currentTimeMillis();
        int spendTime = (int)(endTime - startTime);

        log.setIp(ip);
        log.setMethod(method);
        log.setMethodName(methodName);
        log.setSpendTime(spendTime);
        log.setStartTime(new Date(startTime));
        log.setEndTime(new Date(endTime));
        log.setUri(uri);
        log.setUrl(url);

        JLogger.info(log.toString());

        return result;
    }
}
