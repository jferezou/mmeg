package com.mmeg.glyphes.optimizer.service.impl;

import com.mmeg.glyphes.optimizer.pojo.Glyphage;
import com.mmeg.glyphes.optimizer.pojo.OptimizeParameters;
import com.mmeg.glyphes.optimizer.pojo.Solution;
import com.mmeg.glyphes.optimizer.pojo.glyphes.Glyphe;
import com.mmeg.glyphes.optimizer.service.VerificationContrainte;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Service
public class VerificationContrainteImpl implements VerificationContrainte {
	@Override
	public boolean verifierContraintes(final Solution solution, final OptimizeParameters optimizeParameters) {

		Glyphage glyphage = solution.getGlyphage();
		List<Glyphe> glyphes = Arrays.asList(glyphage.getSlotCarre1(), glyphage.getSlotCarre2(), glyphage.getSlotRond1(), glyphage.getSlotRond2(), glyphage.getSlotHexa1(), glyphage.getSlotHexa2());

		boolean carreOk = glyphes.stream().map(Glyphe::getCarre).reduce(0, Integer::sum).equals(2);
		boolean rondOk = glyphes.stream().map(Glyphe::getRond).reduce(0, Integer::sum).equals(2);
		boolean hexaOk = glyphes.stream().map(Glyphe::getHexagonal).reduce(0, Integer::sum).equals(2);

		boolean isValid = carreOk && rondOk && hexaOk;

		// gestion des stats
		isValid &= checkStatOk(optimizeParameters.getMinPointDeVie(), solution.getPvTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinDefense(), solution.getDefTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinAttaque(), solution.getAttTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinVitesse(), solution.getVitesseTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinDegatCoupsCritiques(), solution.getDccTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinTauxCoupsCritiques(), solution.getCcTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinPrecision(), solution.getPrecisionTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinResistance(), solution.getResistanceTotaux());

		// gestion des sets
		isValid &= setValid(glyphes, optimizeParameters.getNbSetVitalite(),Glyphe::getSetVitalite);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetDestruction(),Glyphe::getSetDestruction);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetPuissance(),Glyphe::getSetPuissance);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetDefense(),Glyphe::getSetDefense);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetFrenesie(),Glyphe::getSetFrenesie);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetAdresse(),Glyphe::getSetAdresse);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetApaisement(),Glyphe::getSetApaisement);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetDrainVie(),Glyphe::getSetDrainDeVie);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetEndurance(),Glyphe::getSetEndurance);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetImmunite(),Glyphe::getSetImmunite);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetMeditation(),Glyphe::getSetMeditation);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetRapidite(),Glyphe::getSetRapidite);


		return isValid;
	}

	private boolean checkStatOk(final int minValue, final long statsValue) {
		return minValue <= 0 ? true : statsValue > minValue;
	}

	protected boolean setValid(final List<Glyphe> glyphes, final int nbSet, final Function<Glyphe, Integer> set) {
		int nbSetGlyphes = glyphes.stream().map(set).reduce(0, Integer::sum) / 3;

		return nbSetGlyphes >= nbSet;
	}

}
