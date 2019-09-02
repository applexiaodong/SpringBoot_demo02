package com.dong.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description
 * @author: wangxd
 * @create: 2019-08-27
 **/
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.dong.demo.controller.GirlController.*(..))")
    public void log(){
    }

    //获取http请求内容
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("I love you");
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("url={}",request.getRequestURL());//url
        logger.info("ip={}",request.getRemoteAddr());//ip
        logger.info("method={}",request.getMethod());//method
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringType()+"."+joinPoint.getSignature().getName());//类方法
        logger.info("class_args={}",joinPoint.getArgs());//参数
    }

    @After("log()")
    public void doAfter(){
        logger.info("You like me");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturning(Object object){
        logger.info("response={}",object.toString());
    }
}
