package com.mmeg.glyphes.optimizer.pojo.glyphes;

import lombok.Data;

@Data
public abstract class Glyphe {
	protected int pvPourcent;
	protected int pvFlat;
	protected int vitesse;
	protected int defPourcent;
	protected int defFlat;
	protected int precision;
	protected int resistance;
	protected int cc;
	protected int dcc;
	protected int attPourcent;
	protected int attFlat;

	protected int rond;
	protected int carre;
	protected int hexagonal;


	protected int setVitalite;
	protected int setRapidite;
	protected int setDrainDeVie;
	protected int setPuissance;
	protected int setFrenesie;
	protected int setDefense;
	protected int setAdresse;
	protected int setDestruction;
	protected int setEndurance;
	protected int setApaisement;
	protected int setMeditation;
	protected int setImmunite;

	protected int equipee;
}
