package com.parasol.BaaS.aop;

import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class ServiceLogger {

    @Pointcut("execution(* com.parasol.BaaS.service.*.*(..))")
    public void servicePointcut() {
    }

    @Around("servicePointcut()")
    public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();
        Method method = signature.getMethod();
        Object[] paramValues = joinPoint.getArgs();

        String paramKey;
        Object paramValue;
        Map<String, Object> params = new HashMap<>();

        for (int i = 0; i < method.getParameterCount(); i++) {
            paramKey = method.getParameters()[i].getName();
            paramValue = paramValues[i].toString();
            params.put(paramKey, paramValue);
        }

        log.info(
                "Service :: {} :: {} :: {}",
                LocalDateTime.now(),
                methodName,
                params
        );

        return joinPoint.proceed();

    }

    private static JSONObject getParams(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            String replaceParam = param.replaceAll("\\.", "-");
            jsonObject.put(replaceParam, request.getParameter(param));
        }
        return jsonObject;
    }
}