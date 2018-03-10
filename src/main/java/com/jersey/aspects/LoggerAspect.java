/**
 * 
 */
package com.jersey.aspects;

import org.aspectj.lang.JoinPoint;

public class LoggerAspect {
	/**
	 * Log entry.
	 *
	 * @param joinPoint
	 *            the join point
	 */
	public void logEntryInServiceLayer(JoinPoint joinPoint) {
		System.out.println("Entering method " + joinPoint.getTarget().getClass().getSimpleName() + "."
				+ joinPoint.getSignature().getName() + "...");
		Object[] listOfArgs = joinPoint.getArgs();
		System.out.println("Arguments for " + joinPoint.getSignature().getName() + "()");
		for (Object arg : listOfArgs) {
			if(arg!=null)
			System.out.println(arg.toString());
		}
	}

	/**
	 * Log exit.
	 *
	 * @param joinPoint
	 *            the join point
	 */
	// @After(POINT_CUT_EXPRESSION_SERVICE_LAYER)
	public void logExitInServiceLayer(JoinPoint joinPoint) {
		System.out.println("Exiting method " + joinPoint.getTarget().getClass().getSimpleName() + "."
				+ joinPoint.getSignature().getName() + "...");
	}
    
	/**
	 * This method will be invoked while doing logging when exception is thrown 
	 * by the application.
	 * 
	 * @param joinPoint
	 */
	public void logExitAfterThrowing(JoinPoint joinPoint) {
		System.out.println("Exception Throwing method " + joinPoint.getTarget().getClass().getSimpleName() + "."
				+ joinPoint.getSignature().getName() + "...");
		Object[] listOfArgs = joinPoint.getArgs();
		System.out.println("Arguments for " + joinPoint.getSignature().getName() + "()");
		for (Object arg : listOfArgs) {
			System.out.println(arg.toString());
		}
	}

}
