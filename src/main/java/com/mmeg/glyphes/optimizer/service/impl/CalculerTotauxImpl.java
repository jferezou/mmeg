package com.mmeg.glyphes.optimizer.service.impl;

import com.mmeg.glyphes.optimizer.annotation.LogCall;
import com.mmeg.glyphes.optimizer.pojo.Glyphage;
import com.mmeg.glyphes.optimizer.pojo.OptimizeParameters;
import com.mmeg.glyphes.optimizer.pojo.Solution;
import com.mmeg.glyphes.optimizer.pojo.glyphes.Glyphe;
import com.mmeg.glyphes.optimizer.service.CalculerTotaux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static com.mmeg.glyphes.optimizer.utils.Constantes.*;

@Service
public class CalculerTotauxImpl implements CalculerTotaux {
	private static final Logger LOGGER = LoggerFactory.getLogger(CalculerTotauxImpl.class);

	@Override
	@LogCall
	public void mettreAjourTotaux(final Solution solution) {
		Glyphage glyphage = solution.getGlyphage();

		List<Glyphe> glyphes = Arrays.asList(glyphage.getSlotCarre1(), glyphage.getSlotCarre2(), glyphage.getSlotRond1(), glyphage.getSlotRond2(), glyphage.getSlotHexa1(), glyphage.getSlotHexa2());

		solution.setPvTotaux(totalPourcentEtFlat(glyphes, solution.getMob().getPvBase(), Glyphe::getPvPourcent, Glyphe::getPvFlat, Glyphe::getSetVitalite, BONUS_VITALITE));
		solution.setDefTotaux(totalPourcentEtFlat(glyphes, solution.getMob().getDefBase(), Glyphe::getDefPourcent, Glyphe::getDefFlat, Glyphe::getSetDefense, BONUS_DEFENSE));
		solution.setAttTotaux(totalPourcentEtFlat(glyphes, solution.getMob().getAttBase(), Glyphe::getAttPourcent, Glyphe::getAttFlat, Glyphe::getSetPuissance, BONUS_PUISSANCE));

		solution.setVitesseTotaux(totalPourcent(glyphes, solution.getMob().getVitesseBase(), Glyphe::getVitesse, Glyphe::getSetRapidite, BONUS_RAPIDITE));

		solution.setCcTotaux(totalFlat(glyphes, solution.getMob().getCcBase(), Glyphe::getCc, Glyphe::getSetFrenesie, BONUS_FRENESIE));
		solution.setDccTotaux(totalFlat(glyphes, solution.getMob().getDccBase(), Glyphe::getDcc, Glyphe::getSetDestruction, BONUS_DESTRUCTION));
		solution.setPrecisionTotaux(totalFlat(glyphes, solution.getMob().getPrecisionBase(), Glyphe::getPrecision, Glyphe::getSetAdresse, BONUS_ADRESSE));
		solution.setResistanceTotaux(totalFlat(glyphes, solution.getMob().getResistanceBase(), Glyphe::getResistance, Glyphe::getSetEndurance, BONUS_ENDURANCE));
		LOGGER.debug("Resultat calcul : {}", solution.toString());
	}

	protected long totalPourcentEtFlat(final List<Glyphe> glyphes, final int base, final Function<Glyphe, Integer> pourcent, final Function<Glyphe, Integer> flat, final Function<Glyphe, Integer> set, final double bonusSet) {
		int totalPourcentGlyphes = glyphes.stream().map(pourcent).reduce(0, Integer::sum);
		int totalFlatGlyphes = glyphes.stream().map(flat).reduce(0, Integer::sum);
		int bonus = calculateBonusSet(glyphes.stream().map(set).reduce(0, Integer::sum));

		long total = (int)((convertToPercent(totalPourcentGlyphes) * base + totalFlatGlyphes) * (1 + bonusSet * bonus));

		return  total;
	}

	protected int calculateBonusSet(final int sum) {
		return sum / 3;
	}

	protected double convertToPercent(int valueToconvert) {
		return (1+valueToconvert/100.0);
	}

	protected long totalPourcent(final List<Glyphe> glyphes, final int base, final Function<Glyphe, Integer> pourcent, final Function<Glyphe, Integer> set, final double bonusSet) {
		int totalPourcentGlyphes = glyphes.stream().map(pourcent).reduce(0, Integer::sum);
		int bonus = calculateBonusSet(glyphes.stream().map(set).reduce(0, Integer::sum));

		long total = (int)((convertToPercent(totalPourcentGlyphes) * base) * (1 + bonusSet * bonus));

		return  total;
	}

	protected long totalFlat(final List<Glyphe> glyphes, final int base, final Function<Glyphe, Integer> flat, final Function<Glyphe, Integer> set, final double bonusSet) {
		int totalFlatGlyphes = glyphes.stream().map(flat).reduce(0, Integer::sum);
		int bonus = calculateBonusSet(glyphes.stream().map(set).reduce(0, Integer::sum));

		long total = (int)((base + totalFlatGlyphes) * (1 + bonusSet * bonus));

		return  total;
	}

	@Override
	public double calculerValeurObjectif(final Solution solution, final OptimizeParameters optimizeParameters) {
		double objectif = calculRatio(solution.getPvTotaux(), solution.getMob().getPvBase()) * optimizeParameters.getPoidHp();
		objectif += calculRatio(solution.getDefTotaux(), solution.getMob().getDefBase()) * optimizeParameters.getPoidDef();
		objectif += calculRatio(solution.getAttTotaux(), solution.getMob().getAttBase()) * optimizeParameters.getPoidAtt();
		objectif += calculRatio(solution.getVitesseTotaux(), solution.getMob().getVitesseBase()) * optimizeParameters.getPoidVitesse();
		objectif += calculRatio(solution.getCcTotaux(), solution.getMob().getCcBase()) * optimizeParameters.getPoidCC();
		objectif += calculRatio(solution.getDccTotaux(), solution.getMob().getDccBase()) * optimizeParameters.getPoidDCC();
		objectif += calculRatio(solution.getPrecisionTotaux(), solution.getMob().getPrecisionBase()) * optimizeParameters.getPoidPrecision();
		objectif += calculRatio(solution.getResistanceTotaux(), solution.getMob().getResistanceBase()) * optimizeParameters.getPoidResistance();

		return objectif;
	}

	protected double calculRatio(final long total, final int base) {
		double value = (total - base) / (double) base;
		int decimalPlaces = 5;
		int truncatedNumberInt = (int)( value * Math.pow( 10, decimalPlaces ) );
		double truncatedNumber = (double)( truncatedNumberInt / Math.pow( 10, decimalPlaces ) );
		return truncatedNumber;
	}
}
