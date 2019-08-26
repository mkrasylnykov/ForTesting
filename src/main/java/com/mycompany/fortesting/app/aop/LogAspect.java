/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fortesting.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Admin
 */
@Aspect
@Configuration
public class LogAspect {
    //@Around("@annotation(com.mycompany.fortesting.app.aop.TrackTime)")
    @Around("execution(* com.mycompany.fortesting.app.service.CarService.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object ret = joinPoint.proceed();

        long timeTaken = System.currentTimeMillis() - startTime;
        System.out.println("Time Taken by " + joinPoint + " is " + timeTaken);
        return ret;
    }
}
