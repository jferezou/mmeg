package com.mmeg.glyphes.optimizer.config.aspects;

import com.mmeg.glyphes.optimizer.annotation.LogCall;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Un aspect appliquer à toutes les méthodes que l'on souhaite tracer
 * 
 */
@Aspect
public class LogCallAspect {

	/**
	 * Intercepte les méthodes public annotées de @LogCall et trace les débuts/fins. Pas possible d'interpecter les méthodes privées.
	 * 
	 * @param pjp
	 *            le joint point
	 * @param logCall
	 *            l'annotation interceptée
	 * @return le résultat de la fonction intercepté
	 * @throws Throwable
	 */
	@Around("@annotation(logCall)")
	public Object executeInApplicationContext(final ProceedingJoinPoint pjp, final LogCall logCall) throws Throwable {
		Logger log = LogManager.getLogger(logCall.loggerName());
		Object proceed;
		if (log.isDebugEnabled()) {
			String className = pjp.getTarget().getClass().getSimpleName();
			String methodName = pjp.getSignature().getName();

			log.debug("DEBUT {}.{}({})", className, methodName, pjp.getArgs());

			long start = System.currentTimeMillis();
			proceed = pjp.proceed();
			long end = System.currentTimeMillis();

			log.debug("FIN {}.{}()[{}ms]", className, methodName, (end - start));
		} else {
			proceed = pjp.proceed();
		}

		return proceed;
	}
}
