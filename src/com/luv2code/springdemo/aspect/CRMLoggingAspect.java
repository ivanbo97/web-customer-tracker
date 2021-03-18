package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;




@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declarations
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forServicePackage()||forControllerPackage()||forDaoPackage()")
	private void forAppFlow() {}
	
	// add @Before advice 
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		
		// display the method we are calling
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("====>>> in @Before advice: calling method: " + method);
		
		// display the arguments to the method
		Object[] args = joinPoint.getArgs();
		for(Object arg : args) {
			myLogger.info("===>>> argument: " + arg);
		}
		
	}
	
	// add @AfterRunning advice
	@AfterReturning(
			pointcut = "forAppFlow()",
			returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		
		// display the method that we are returning from
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("====>>> in @AfterReturning advice: from method: " + method);
		
		// display the returned data
		myLogger.info("===>> result: " + result);
	}
	
}
