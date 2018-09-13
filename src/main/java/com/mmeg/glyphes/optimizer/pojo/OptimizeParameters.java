package com.mmeg.glyphes.optimizer.pojo;

import lombok.Data;

@Data
public class OptimizeParameters {

	private String nomMob;
	private String elementMob;
	private String premierSet;
	private String secondSet;
	private int minAttaque;
	private int minVitesse;
	private int minPointDeVie;
	private int minPrecision;
	private int minResistance;
	private int minDegatCoupsCritiques;
	private int minTauxCoupsCritiques;
}
