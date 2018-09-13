package com.mmeg.glyphes.optimizer.pojo.glyphes;

import lombok.Data;

@Data
public abstract class Glyphe {

	public Glyphe(final Glyphe glyphe) {
		pvPourcent = glyphe.getPvPourcent();
		pvFlat = glyphe.getPvFlat();
		vitesse = glyphe.getVitesse();
		defPourcent = glyphe.getDefPourcent();
		defFlat = glyphe.getDefFlat();
		precision = glyphe.getPrecision();
		resistance = glyphe.getResistance();
		cc = glyphe.getCc();
		dcc = glyphe.getDcc();
		attPourcent = glyphe.getAttPourcent();
		attFlat = glyphe.getAttFlat();
		rond = glyphe.getRond();
		carre = glyphe.getCarre();
		hexagonal = glyphe.getHexagonal();
		setVitalite = glyphe.getSetVitalite();
		setRapidite = glyphe.getSetRapidite();
		setDrainDeVie = glyphe.getSetDrainDeVie();
		setPuissance = glyphe.getSetPuissance();
		setFrenesie = glyphe.getSetFrenesie();
		setDefense = glyphe.getSetDefense();
		setAdresse = glyphe.getSetAdresse();
		setDestruction = glyphe.getSetDestruction();
		setEndurance = glyphe.getSetEndurance();
		setApaisement = glyphe.getSetApaisement();
		setMeditation = glyphe.getSetMeditation();
		setImmunite = glyphe.getSetImmunite();
		equipee = glyphe.getEquipee();

	}

	public Glyphe() {

	}

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
