package com.mygaienko.practice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by enda1n on 25.07.2017.
 */
@Component
@Aspect
public class HelloAspect {

    @Before("execution(* com.mygaienko.practice.service.HelloService.execute(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("====================\n====================\n====================\n");
        System.out.println("logBefore is running\n");
        System.out.println("====================\n====================\n====================\n");
    }
}
