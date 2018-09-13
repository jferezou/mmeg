package com.mmeg.glyphes.optimizer.exception.mapper;

import com.mmeg.glyphes.optimizer.exception.TechniqueException;

/**
 * Permet de mapper une exception particulière et de la réifier en une autre exception.
 * 
 * @author Romain GERVAIS
 * 
 */
public interface ExceptionMapper {

	/**
	 * 
	 * @param throwable
	 *            la throwable à matcher
	 * @return true si l'exception est gérée par ce mapper
	 */
	public boolean match(Throwable throwable);

	/**
	 * Réification de l'exception en une autre exception.
	 * 
	 * @param throwable
	 *            la throwable à transformer
	 * @return l'exception réifiée
	 */
	public TechniqueException reify(Throwable throwable);
}
