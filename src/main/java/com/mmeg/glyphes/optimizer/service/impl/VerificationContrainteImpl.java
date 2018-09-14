package com.mmeg.glyphes.optimizer.service.impl;

import com.mmeg.glyphes.optimizer.pojo.Glyphage;
import com.mmeg.glyphes.optimizer.pojo.OptimizeParameters;
import com.mmeg.glyphes.optimizer.pojo.Solution;
import com.mmeg.glyphes.optimizer.pojo.glyphes.Glyphe;
import com.mmeg.glyphes.optimizer.service.VerificationContrainte;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Service
public class VerificationContrainteImpl implements VerificationContrainte {
	private static final Logger LOGGER = LoggerFactory.getLogger(VerificationContrainteImpl.class);
	@Override
	public boolean verifierContraintes(final Solution solution, final OptimizeParameters optimizeParameters) {

		Glyphage glyphage = solution.getGlyphage();
		List<Glyphe> glyphes = Arrays.asList(glyphage.getSlotCarre1(), glyphage.getSlotCarre2(), glyphage.getSlotRond1(), glyphage.getSlotRond2(), glyphage.getSlotHexa1(), glyphage.getSlotHexa2());

		boolean carreOk = glyphes.stream().map(Glyphe::getCarre).reduce(0, Integer::sum).equals(2);
		boolean rondOk = glyphes.stream().map(Glyphe::getRond).reduce(0, Integer::sum).equals(2);
		boolean hexaOk = glyphes.stream().map(Glyphe::getHexagonal).reduce(0, Integer::sum).equals(2);

		boolean isValid = carreOk && rondOk && hexaOk;
		LOGGER.debug("Les emplacements de glyphes sont ok : {}", isValid);

		// gestion des stats
		isValid &= checkStatOk(optimizeParameters.getMinPointDeVie(), solution.getPvTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinDefense(), solution.getDefTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinAttaque(), solution.getAttTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinVitesse(), solution.getVitesseTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinDegatCoupsCritiques(), solution.getDccTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinTauxCoupsCritiques(), solution.getCcTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinPrecision(), solution.getPrecisionTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinResistance(), solution.getResistanceTotaux());
		LOGGER.debug("Les stats sont ok : {}", isValid);

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
		LOGGER.debug("Les set sont ok : {}", isValid);


		return isValid;
	}


	@Override
	public boolean verifierContraintesRelachees(final Solution solution, final OptimizeParameters optimizeParameters, final double ratioMin, final int nbGlyphesMinParset) {

		Glyphage glyphage = solution.getGlyphage();
		List<Glyphe> glyphes = Arrays.asList(glyphage.getSlotCarre1(), glyphage.getSlotCarre2(), glyphage.getSlotRond1(), glyphage.getSlotRond2(), glyphage.getSlotHexa1(), glyphage.getSlotHexa2());


		boolean isValid = true;

		// gestion des stats
		isValid &= checkStatOk(optimizeParameters.getMinPointDeVie() * ratioMin, solution.getPvTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinDefense() * ratioMin, solution.getDefTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinAttaque() * ratioMin, solution.getAttTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinVitesse() * ratioMin, solution.getVitesseTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinDegatCoupsCritiques() * ratioMin, solution.getDccTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinTauxCoupsCritiques() * ratioMin, solution.getCcTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinPrecision() * ratioMin, solution.getPrecisionTotaux());
		isValid &= checkStatOk(optimizeParameters.getMinResistance() * ratioMin, solution.getResistanceTotaux());

		// gestion des sets
		isValid &= setValid(glyphes, optimizeParameters.getNbSetVitalite(),Glyphe::getSetVitalite,nbGlyphesMinParset);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetDestruction(),Glyphe::getSetDestruction,nbGlyphesMinParset);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetPuissance(),Glyphe::getSetPuissance,nbGlyphesMinParset);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetDefense(),Glyphe::getSetDefense,nbGlyphesMinParset);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetFrenesie(),Glyphe::getSetFrenesie,nbGlyphesMinParset);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetAdresse(),Glyphe::getSetAdresse,nbGlyphesMinParset);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetApaisement(),Glyphe::getSetApaisement,nbGlyphesMinParset);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetDrainVie(),Glyphe::getSetDrainDeVie,nbGlyphesMinParset);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetEndurance(),Glyphe::getSetEndurance,nbGlyphesMinParset);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetImmunite(),Glyphe::getSetImmunite,nbGlyphesMinParset);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetMeditation(),Glyphe::getSetMeditation,nbGlyphesMinParset);
		isValid &= setValid(glyphes, optimizeParameters.getNbSetRapidite(),Glyphe::getSetRapidite,nbGlyphesMinParset);


		return isValid;
	}

	private boolean checkStatOk(final double minValue, final long statsValue) {
		return minValue <= 0 ? true : statsValue > minValue;
	}

	protected boolean setValid(final List<Glyphe> glyphes, final int nbSet, final Function<Glyphe, Integer> set) {
		return setValid(glyphes, nbSet, set, 3);
	}

	protected boolean setValid(final List<Glyphe> glyphes, final int nbSet, final Function<Glyphe, Integer> set, final int nbGlyphesMinParset) {
		int nbSetGlyphes = glyphes.stream().map(set).reduce(0, Integer::sum) / nbGlyphesMinParset;

		return nbSetGlyphes >= nbSet;
	}

}
