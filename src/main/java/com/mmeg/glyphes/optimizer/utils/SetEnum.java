package com.mmeg.glyphes.optimizer.utils;

public enum SetEnum implements CodeEnum<String> {

	VITALITE("vitalite"),
	PUISSANCE("Puissance"),
	FRENESIE("frenesie"),
	DEFENSE("defense"),
	RAPIDITE("rapidite"),
	ADRESSE("adresse"),
	DESTRUCTION("destruction"),
	ENDURANCE("endurance"),
	DRAINDEVIE("drain_de_vie"),
	APAISEMENT("apaisement"),
	MEDITATION("meditation"),
	IMMUNITE("immunite"),
	AUCUN("aucun");

	/** Le code du type  */
	private String code;

	/**
	 * Constructeur
	 *
	 * @param code
	 *            Le code du type
	 */
	private SetEnum(final String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}
}
