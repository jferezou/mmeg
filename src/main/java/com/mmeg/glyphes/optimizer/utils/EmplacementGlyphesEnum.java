package com.mmeg.glyphes.optimizer.utils;

public enum EmplacementGlyphesEnum implements CodeEnum<String> {

	CARRE1("CARRE1"),
	CARRE2("CARRE2"),
	ROND1("ROND1"),
	ROND2("ROND2"),
	HEXA1("HEXA1"),
	HEXA2("HEXA2");

	/** Le code du type */
	private String code;

	/**
	 * Constructeur
	 *
	 * @param code
	 *            Le code du type
	 */
	private EmplacementGlyphesEnum(final String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}
}
