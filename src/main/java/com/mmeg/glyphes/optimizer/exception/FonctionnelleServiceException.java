package com.mmeg.glyphes.optimizer.exception;

public class FonctionnelleServiceException extends ServiceException {

	private String code;

	/**
	 * Constructeur pour les librairies de parsing, à ne pas utiliser dans notre code
	 */
	FonctionnelleServiceException() {

	}

	/**
	 * Constructeur
	 *
	 * @param e
	 */
	public FonctionnelleServiceException(FonctionnelleException e) {
		super(e);
		this.code = e.getCode();
	}

	/**
	 * Getter
	 *
	 * @return le code de l'exception fonctionnelle
	 */
	public String getCode() {
		return code;
	}
}

