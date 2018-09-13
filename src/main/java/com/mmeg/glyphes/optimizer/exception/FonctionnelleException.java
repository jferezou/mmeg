package com.mmeg.glyphes.optimizer.exception;


/**
 * Enveloppe pour les exception fonctionnelles.
 */
public class FonctionnelleException extends AbstractApplicationException implements FonctionnelleExceptionContent {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4027150744711819606L;

	/**
	 * Le code de l'erreur fonctionnelle. Exemple : PRR.1 ou RSS.12
	 */
	private String code;

	/**
	 * @param message
	 *            Le message à afficher
	 * @param cause
	 *            L'exception d'origine
	 */
	public FonctionnelleException(FonctionnelleExceptionContent exceptionContent, final Throwable cause) {
		this(exceptionContent.getCode(), exceptionContent.getMessage(), cause);
	}

	/**
	 * @param message
	 *            Le message à afficher
	 * @param cause
	 *            L'exception d'origine
	 */
	public FonctionnelleException(final String code, final String message, final Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	/**
	 * @param message
	 *            Le message à afficher
	 */
	public FonctionnelleException(FonctionnelleExceptionContent exceptionContent) {
		this(exceptionContent.getCode(), exceptionContent.getMessage());
	}

	/**
	 * @param message
	 *            Le message à afficher
	 */
	public FonctionnelleException(final String code, final String message) {
		super(message);
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}
}
