package com.mmeg.glyphes.optimizer.exception;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;

import javax.ws.rs.core.Response;
/**
 * Permet de gérer les exception qui ont lieu dans les services
 *
 * @author Romain GERVAIS
 */
public interface ServiceExceptionHandler {
	String MSG_FONCTIONNEL = "Une erreur fonctionnelle a eu lieu : {} (id = {})";
	String MSG_TECHNIQUE = "Une erreur technique a eu lieu. Id = {}";

	/**
	 * Réifie une throwable en technique exception
	 *
	 * @param throwable la throwable à réifier
	 * @return l'exception réifiée
	 */
	TechniqueException reifyAsTechniqueException(Throwable throwable);

	/**
	 * Réifie une fonctionnelle exception
	 *
	 * @param exception l'exception à réifier
	 * @return l'exception réifiée
	 */
	FonctionnelleException reifyAsFonctionnelleException(FonctionnelleException exception);

	default TechniqueException handleTechniqueException(Throwable throwable,
														final Logger logger) {
		// Si on a une sous classe d'Error (erreur système : OOM et autres) on laisse remonter sans gestion.
		ExceptionUtils.propagateIfInstanceOf(throwable, Error.class);
		// Sinon
		TechniqueException exceptionTechnique = reifyAsTechniqueException(throwable);
		Message message = logger.getMessageFactory().newMessage(MSG_TECHNIQUE, exceptionTechnique.getId());
		logger.error(message, exceptionTechnique);
		return exceptionTechnique;
	}

	default FonctionnelleException handleFonctionnelleException(FonctionnelleException fonctionnelleException, final Logger logger) {
		FonctionnelleException reifiedfonctionnelleException = reifyAsFonctionnelleException(fonctionnelleException);
		logger.info(MSG_FONCTIONNEL, fonctionnelleException.getMessage(), fonctionnelleException.getId());
		return reifiedfonctionnelleException;
	}

	default Response asErrorResponse(TechniqueException exception) {
		return Response.serverError().entity(ServiceResponse.from(exception)).build();
	}

	default Response asErrorResponse(FonctionnelleException exception) {
		return Response.status(Response.Status.BAD_REQUEST).entity(ServiceResponse.from(exception)).build();
	}
}