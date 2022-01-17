package org.polik.springmvc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* org.polik.springmvc.dao.*.*(..))")
    public Object aroundAllRepositoryMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();

        System.out.println(proceedingJoinPoint.getThis().getClass().getSimpleName());
        System.out.println("Begin of " + className + methodName + " working");
        Object result = proceedingJoinPoint.proceed();
        System.out.println("End of " + className + methodName + " working");

        return result;
    }
}
