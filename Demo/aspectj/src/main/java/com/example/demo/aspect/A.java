package com.example.demo.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class A {
    private static final Logger logger = LoggerFactory.getLogger(A.class);

    @Pointcut("execution(* com.example.demo.aspect..*(..))")
    private void pointcut(){};

    @Before("pointcut()")
    public void before(){
        String info = "@Before : 通知方法会在目标方法调用之前执行";
        logger.info(info);
    }
    @After("pointcut()")
    public void after(){
        String info = "@After : 通知方法会在目标方法返回或抛出异常后调用";
        logger.info(info);
    }
    @AfterReturning("pointcut()")
    public void afterReturning(){
        String info = "@AfterReturning : 通知方法会在目标方法返回后调用";
        logger.info(info);
    }
    @AfterThrowing("pointcut()")
    public void afterThrowing(){
        String info = "@AfterThrowing : 通知会在目标方法抛出异常后调用";
        logger.info(info);
    }
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint){
        Object result = null;
        String info = "@Around : 通知方法会将目标方法封装起来";
        logger.info(info);
        try {
            logger.info("1");
            result = joinPoint.proceed(); //执行目标方法
            logger.info("2");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
