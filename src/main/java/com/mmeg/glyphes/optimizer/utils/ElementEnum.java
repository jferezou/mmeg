package com.mmeg.glyphes.optimizer.utils;

public enum ElementEnum implements CodeEnum<String> {

	TERRE("earth"),
	EAU("water"),
	AIR("air"),
	FEU("fire");

	/** Le code du type */
	private String code;

	/**
	 * Constructeur
	 *
	 * @param code
	 *            Le code du type
	 */
	private ElementEnum(final String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}
}
