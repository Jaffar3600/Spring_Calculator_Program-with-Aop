package com.cg.app.validation;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorValidationAspect {
	private static Logger logger = Logger.getLogger(CalculatorValidationAspect.class.getName());

	@Before("execution(* com.cg.app.calculator.Calculator.*(..))")
	public void log1() {
		logger.info("Before-logging the method");
	}

	@After("execution(* com.cg.app.calculator.Calculator.*(..))")
	public void log2() {
		logger.info("After-logging the method");
	}

	@Around("execution(* com.cg.app.calculator.Calculator.*(..))")
	public Object log3(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Before logging the method");
		logger.info("Function name is: " + pjp.getSignature());
		logger.info("parameters are: ");
		Object[] params = pjp.getArgs();
		Object returnValue = null;
		for (int i = 0; i < params.length; i++) {
			logger.info("parameter value at index" + i + "is: " + params[i]);
			if ((Integer) params[i] != 0 && (Integer) params[i + 1] != 0) {
				returnValue = pjp.proceed();
			} else {
				logger.info("Invalid input addition not possible");
				break;
			}
		}
		logger.info("after logging method");
		return returnValue;
	}

	@AfterReturning(pointcut = "execution(* com.cg.app.calculator.Calculator.*(..))", returning = "retval")
	public void log4(Integer retval) {
		logger.info("value is: " + retval);
	}

	@AfterThrowing(pointcut = "execution(* com.cg.app.calculator.Calculator.*(..))", throwing = "ex")
	public void log5(ArithmeticException ex) {
		logger.info("Invalid input");
	}

}
