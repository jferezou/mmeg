package com.mmeg.glyphes.optimizer.exception.mapper;

import com.mmeg.glyphes.optimizer.exception.ApplicationExceptionFactory;
import com.mmeg.glyphes.optimizer.exception.TechniqueException;
import org.apache.commons.lang3.StringUtils;

/**
 * Un mapper qui recherche des exception de type timeout
 * 
 * @author Romain GERVAIS
 * 
 */
public class TimeoutExceptionMapper implements ExceptionMapper {

	/** La factory d'exception */
	private final ApplicationExceptionFactory applicationExceptionFactory;

	/**
	 * Constructeur
	 * 
	 * @param applicationExceptionFactory
	 *            l'applicationExceptionFactory
	 */
	public TimeoutExceptionMapper(ApplicationExceptionFactory applicationExceptionFactory) {
		super();
		this.applicationExceptionFactory = applicationExceptionFactory;
	}

	@Override
	public boolean match(Throwable throwable) {
		return StringUtils.containsIgnoreCase(throwable.getClass().getSimpleName(), "timeout");
	}

	@Override
	public TechniqueException reify(Throwable throwable) {
		return applicationExceptionFactory.newTechniqueException("Le service n'a pas pu répondre dans le temps imparti.", throwable);
	}
}
