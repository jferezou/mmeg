package com.mmeg.glyphes.optimizer.exception;
public abstract class AbstractApplicationException extends RuntimeException implements ApplicationException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1771875297724820589L;

	/**
	 * Un identifiant unique permettant d'identifier une exception facilement
	 */
	private String exceptionId = null;

	/**
	 * Constructeur pour
	 */
	public AbstractApplicationException() {
		super();
	}

	/**
	 * @param message
	 *            Le message à afficher
	 * @param cause
	 *            L'exception d'origine
	 */
	public AbstractApplicationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 *            Le message à afficher
	 */
	public AbstractApplicationException(final String message) {
		super(message);
	}

	/**
	 * @param cause
	 *            L'exception d'origine
	 */
	public AbstractApplicationException(final Throwable cause) {
		super(cause);
	}

	@Override
	public boolean hasId() {
		return exceptionId != null;
	}

	@Override
	public boolean hasNoId() {
		return !hasId();
	}

	public String getId() {
		return exceptionId;
	}

	public void setId(String exceptionId) {
		this.exceptionId = exceptionId;
	}
}