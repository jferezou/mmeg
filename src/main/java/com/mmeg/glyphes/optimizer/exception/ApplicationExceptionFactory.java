package com.mmeg.glyphes.optimizer.exception;

import java.util.UUID;

/**
 * Permet d'initialiser une exception et de lui attribuer un identifiant unique. Il est aussi possible de créer une exception avec "new" directement
 * mais elle n'aura pas d'identifiant. Il faudra appeler une des methodes {@link #reifyFonctionnelleException(Throwable)} ou
 * {@link #reifyTechniqueException(Throwable)} de cette classe pour s'assurer que l'exception a bien un id unique.
 * 
 * @author Romain GERVAIS
 * 
 */
public class ApplicationExceptionFactory {

	/**
	 * @param message
	 *            Le message à afficher
	 * @param cause
	 *            L'exception d'origine
	 * @return exception fonctionnelle avec id unique
	 */
	public FonctionnelleException newFonctionnelleException(final FonctionnelleExceptionContent exceptionContent) {
		FonctionnelleException exception = new FonctionnelleException(exceptionContent);
		exception.setId(generateUniqueIndentifier());
		return exception;
	}

	/**
	 * @param message
	 *            Le message à afficher
	 * @param cause
	 *            L'exception d'origine
	 * @return exception fonctionnelle avec id unique
	 */
	public FonctionnelleException newFonctionnelleException(final FonctionnelleExceptionContent exceptionContent, Object... replacements) {
		FonctionnelleException exception = new FonctionnelleException(exceptionContent.getCode(), format(exceptionContent.getMessage(), replacements));
		exception.setId(generateUniqueIndentifier());
		return exception;
	}

	/**
	 * @param message
	 *            Le message à afficher
	 * @param cause
	 *            L'exception d'origine
	 * @return exception fonctionnelle avec id unique
	 */
	public FonctionnelleException newFonctionnelleException(final FonctionnelleExceptionContent exceptionContent, final Throwable cause) {
		FonctionnelleException exception = new FonctionnelleException(exceptionContent, cause);
		exception.setId(generateUniqueIndentifier());
		return exception;
	}

	/**
	 * @param message
	 *            Le message à afficher
	 * @param cause
	 *            L'exception d'origine
	 * @return exception fonctionnelle avec id unique
	 */
	public FonctionnelleException newFonctionnelleException(FonctionnelleExceptionContent exceptionContent, final Throwable cause,
			Object... replacements) {
		String formatedMessage = format(exceptionContent.getMessage(), replacements);
		FonctionnelleException exception = new FonctionnelleException(exceptionContent.getCode(), formatedMessage, cause);
		exception.setId(generateUniqueIndentifier());
		return exception;
	}

	/**
	 * * Permet d'encapsuler toutes exceptions dans une ExceptionFonctionnelle
	 * 
	 * @param cause
	 *            l'exception qui à été lancée
	 * @return une exception fonctionnelle avec un id unique
	 */
	public FonctionnelleException reifyFonctionnelleException(final FonctionnelleException exception) {
		ensureIdIsSet(exception);
		return exception;
	}

	/**
	 * 
	 * @return une exception technique avec un id unique
	 */
	public TechniqueException newTechniqueException() {
		TechniqueException exception = new TechniqueException();
		exception.setId(generateUniqueIndentifier());
		return exception;
	}

	/**
	 * @param message
	 *            Le message à afficher
	 * @param cause
	 *            L'exception d'origine
	 * @return une exception technique avec un id unique
	 */
	public TechniqueException newTechniqueException(final String message, final Throwable cause, Object... replacements) {
		return newTechniqueException(format(message, replacements), cause);
	}

	/**
	 * @param message
	 *            Le message à afficher
	 * @param cause
	 *            L'exception d'origine
	 * @return une exception technique avec un id unique
	 */
	public TechniqueException newTechniqueException(final String message, final Throwable cause) {
		TechniqueException exception = new TechniqueException(message, cause);
		exception.setId(generateUniqueIndentifier());
		return exception;
	}

	/**
	 * @param message
	 *            Le message à afficher
	 * @return une exception technique avec un id unique
	 */
	public TechniqueException newTechniqueException(final String message, Object... replacements) {
		return newTechniqueException(format(message, replacements));
	}

	/**
	 * @param message
	 *            Le message à afficher
	 * @return une exception technique avec un id unique
	 */
	public TechniqueException newTechniqueException(final String message) {
		TechniqueException exception = new TechniqueException(message);
		exception.setId(generateUniqueIndentifier());
		return exception;
	}

	/**
	 * @param cause
	 *            L'exception d'origine
	 * @return une exception technique avec un id unique
	 */
	public TechniqueException newTechniqueException(final Throwable cause) {
		return newTechniqueException(cause.getMessage(), cause);
	}

	/**
	 * * Permet d'encapsuler toutes exceptions dans une TechniqueException et de s'assurer que l'exception en retour a bien un identifiant unique
	 * 
	 * @param cause
	 *            l'exception qui à été lancée
	 * @return une exception technique avec un id unique
	 */
	public TechniqueException reifyTechniqueException(final Throwable cause) {
		TechniqueException techniqueException = TechniqueException.wrap(cause);
		ensureIdIsSet(techniqueException);
		return techniqueException;
	}

	/**
	 * Assure qu'un id soit renseigné. Si l'id n'est pas renseigné alors un identifiant est généré
	 * 
	 * @param exception
	 *            l'exception pour laquelle on souhaite s'assurer qu'elle a un id renseigné.
	 */
	private void ensureIdIsSet(ApplicationException exception) {
		if (exception.hasNoId()) {
			exception.setId(generateUniqueIndentifier());
		}
	}

	/**
	 * Génère un identifiant unique pour l'affecter à l'exception
	 * 
	 * @return un identifiant unique
	 */
	protected String generateUniqueIndentifier() {
		return UUID.randomUUID().toString();
	}

	protected String format(String message, Object... replacements) {
		return String.format(message, replacements);
	}
}
