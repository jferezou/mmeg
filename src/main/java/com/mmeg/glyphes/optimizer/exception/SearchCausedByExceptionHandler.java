package com.mmeg.glyphes.optimizer.exception;

import com.mmeg.glyphes.optimizer.exception.mapper.ExceptionMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de ServiceExceptionHandler qui recherche certaines exceptions en listant tous les "caused by"
 * 
 * @author Romain GERVAIS
 * 
 */
public class SearchCausedByExceptionHandler implements ServiceExceptionHandler {
	/** l'exception factory */
	private ApplicationExceptionFactory applicationExceptionFactory;
	/**
	 * Des "transformateurs" d'exception
	 */
	public final List<ExceptionMapper> exceptionMappers = new ArrayList<>();

	/**
	 * Constructeur
	 * 
	 * @param applicationExceptionFactory
	 *            l'exception factory
	 */
	public SearchCausedByExceptionHandler(ApplicationExceptionFactory applicationExceptionFactory) {
		this.applicationExceptionFactory = applicationExceptionFactory;
	}

	@Override
	public TechniqueException reifyAsTechniqueException(Throwable serviceException) {
		TechniqueException reifiedException = tryToReifyWithMappers(serviceException);
		if (reifiedException == null) {
			reifiedException = applicationExceptionFactory.newTechniqueException(serviceException);
		}
		return reifiedException;
	}

	/**
	 * Essai de réifier l'exception à partir des exceptions mapper. S'arrête dès qu'une exception est gérée par un mapper.
	 * 
	 * @param serviceException
	 *            l'exception qui a eu lieu dans le service
	 * @return la technique exception réifiée, ou null si non trouvée
	 */
	private TechniqueException tryToReifyWithMappers(Throwable serviceException) {
		TechniqueException reifiedException = null;

		List<Throwable> causedByList = ExceptionUtils.getThrowableList(serviceException);
		for (Throwable causedBy : causedByList) {
			for (ExceptionMapper exceptionMapper : exceptionMappers) {
				if (exceptionMapper.match(causedBy)) {
					reifiedException = exceptionMapper.reify(serviceException);
					break;
				}
			}

			if (reifiedException != null) {
				break;
			}
		}
		return reifiedException;
	}

	@Override
	public FonctionnelleException reifyAsFonctionnelleException(FonctionnelleException e) {
		return applicationExceptionFactory.reifyFonctionnelleException(e);
	}

	/**
	 * Ajoute un Mapper d'exception
	 * 
	 * @param exceptionMapper
	 *            l'exceptionMapper � ajouter
	 */
	public void addExceptionMapper(ExceptionMapper exceptionMapper) {
		exceptionMappers.add(exceptionMapper);
	}
}
