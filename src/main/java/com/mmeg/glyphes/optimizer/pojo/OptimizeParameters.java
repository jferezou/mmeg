package com.mmeg.glyphes.optimizer.pojo;

import com.mmeg.glyphes.optimizer.utils.ElementEnum;
import com.mmeg.glyphes.optimizer.utils.SetEnum;
import lombok.Data;

@Data
public class OptimizeParameters {

	private String nomMob;
	private ElementEnum elementMob;
	private SetEnum premierSet;
	private SetEnum secondSet;
	private int minAttaque;
	private int minVitesse;
	private int minPointDeVie;
	private int minPrecision;
	private int minResistance;
	private int minDegatCoupsCritiques;
	private int minTauxCoupsCritiques;
}
