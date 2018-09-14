package com.mmeg.glyphes.optimizer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indique qu'il faut tracer l'appel à cette méthode
 * 
 * @author Romain GERVAIS
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogCall {

	/**
	 * Le nom du logger à utiliser pour tracer la méthode
	 * 
	 * @return vrai par défaut.
	 */
	String loggerName() default "";
}