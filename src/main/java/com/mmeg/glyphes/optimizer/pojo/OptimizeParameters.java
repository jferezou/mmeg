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
	private int minDefense;
	private int minPrecision;
	private int minResistance;
	private int minDegatCoupsCritiques;
	private int minTauxCoupsCritiques;

	private int poidHp;
	private int poidDef;
	private int poidAtt;
	private int poidVitesse;
	private int poidCC;
	private int poidDCC;
	private int poidPrecision;
	private int poidResistance;

	private int nbSetVitalite = 0;
	private int nbSetDefense = 0;
	private int nbSetPuissance = 0;
	private int nbSetAdresse = 0;
	private int nbSetFrenesie = 0;
	private int nbSetDestruction = 0;
	private int nbSetRapidite = 0;
	private int nbSetImmunite = 0;
	private int nbSetDrainVie = 0;
	private int nbSetApaisement = 0;
	private int nbSetMeditation = 0;
	private int nbSetEndurance = 0;


	public void convertSet() {
		incrementSet(premierSet);
		incrementSet(secondSet);
	}

	private void incrementSet(final SetEnum setEnum) {
		switch(setEnum) {
			case VITALITE:
				nbSetVitalite++;
				break;
			case PUISSANCE:
				nbSetPuissance++;
				break;
			case FRENESIE:
				nbSetFrenesie++;
				break;
			case DEFENSE:
				nbSetDefense++;
				break;
			case RAPIDITE:
				nbSetRapidite++;
				break;
			case ADRESSE:
				nbSetAdresse++;
				break;
			case DESTRUCTION:
				nbSetDestruction++;
				break;
			case ENDURANCE:
				nbSetEndurance++;
				break;
			case DRAINDEVIE:
				nbSetDrainVie++;
				break;
			case APAISEMENT:
				nbSetApaisement++;
				break;
			case MEDITATION:
				nbSetMeditation++;
				break;
			case IMMUNITE:
				nbSetImmunite++;
				break;
			case AUCUN:
				break;
		}

	}
}
