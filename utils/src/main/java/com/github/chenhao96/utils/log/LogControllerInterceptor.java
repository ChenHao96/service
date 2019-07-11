package com.github.chenhao96.utils.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class LogControllerInterceptor {

    @Resource
    private LogService logService;

    @Pointcut("execution(* org.steven.chen.controller.*.*(..))")
    public void initControllerLogAop() {
    }

    @Pointcut("execution(* org.steven.chen.service.impl.*.*(..))")
    public void initServiceLogAop() {
    }

    @Before("initControllerLogAop()")
    public void doBeforeController(JoinPoint joinPoint) {
        logService.info(getTag(joinPoint), joinPoint.getArgs(), null, null);
    }

    @Before("initServiceLogAop()")
    public void doBeforeService(JoinPoint joinPoint) {
        if (beContinue(joinPoint)) return;
        logService.info(getTag(joinPoint), joinPoint.getArgs(), null, null);
    }

    @AfterThrowing(value = "initServiceLogAop()",throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint,Exception e){
        logService.warring(getTag(joinPoint), joinPoint.getArgs(), null, e);
    }

    @AfterReturning(value = "initServiceLogAop()", returning = "returnVal")
    public void doAfterServiceReturn(JoinPoint joinPoint, Object returnVal) {
        if (beContinue(joinPoint)) return;
        logService.info(getTag(joinPoint), joinPoint.getArgs(), returnVal, null);
    }

    public boolean beContinue(JoinPoint joinPoint) {
        Class<?>[] classes = joinPoint.getTarget().getClass().getInterfaces();
        if (classes == null || classes.length == 0) return false;
        for (Class c : classes) {
            if (c.equals(LogService.class)) return true;
        }
        return false;
    }

    public String getTag(JoinPoint joinPoint) {

        StringBuilder result = new StringBuilder();
        result.append(joinPoint.getTarget().getClass().getSimpleName());
        result.append(".");
        result.append(joinPoint.getSignature().getName());
        return result.toString();
    }
}
