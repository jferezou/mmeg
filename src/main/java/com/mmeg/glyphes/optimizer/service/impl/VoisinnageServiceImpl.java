package com.mmeg.glyphes.optimizer.service.impl;

import com.mmeg.glyphes.optimizer.pojo.Glyphage;
import com.mmeg.glyphes.optimizer.pojo.OptimizeParameters;
import com.mmeg.glyphes.optimizer.pojo.Solution;
import com.mmeg.glyphes.optimizer.pojo.glyphes.Glyphe;
import com.mmeg.glyphes.optimizer.pojo.glyphes.GlypheCarre;
import com.mmeg.glyphes.optimizer.pojo.glyphes.GlypheRond;
import com.mmeg.glyphes.optimizer.service.CalculerTotaux;
import com.mmeg.glyphes.optimizer.service.MagasinGlyphesService;
import com.mmeg.glyphes.optimizer.service.VerificationContrainte;
import com.mmeg.glyphes.optimizer.service.VoisinnageService;

import javax.annotation.Resource;
import java.util.List;

public class VoisinnageServiceImpl implements VoisinnageService {

	@Resource
	MagasinGlyphesService magasinGlyphesService;

	@Resource
	VerificationContrainte verificationContrainte;


	@Resource
	CalculerTotaux calculerTotaux;

	@Override
	public Solution parcourirVoisinage(final Solution solutionCourante,final int emplacementAChanger, final OptimizeParameters optimizeParameters, final List<Solution> listeTabou) {

		double valeurSolution = 0.0;
		Solution bestSolution = new Solution();

		if(emplacementAChanger == 1) {
			List<GlypheCarre> listeCarre = magasinGlyphesService.getGlyphesCarre();
			Glyphe glypheUtilise1 = solutionCourante.getGlyphage().getSlotCarre1();
			Glyphe glypheUtilise2 = solutionCourante.getGlyphage().getSlotCarre2();

			for(GlypheCarre nextGlyphe : listeCarre) {
				if(!nextGlyphe.equals(glypheUtilise1) && !nextGlyphe.equals(glypheUtilise2)) {
					Glyphage glyphageTest = new Glyphage(solutionCourante.getGlyphage());
					glyphageTest.setSlotCarre1(nextGlyphe);

					Solution solutionTemp = new Solution();
					solutionTemp.setMob(solutionCourante.getMob());
					solutionTemp.setGlyphage(glyphageTest);
					if(verificationContrainte.verifierContraintes(solutionTemp, optimizeParameters)) {
						calculerTotaux.mettreAjourTotaux(solutionTemp);
						double valeurSolutionTemp = calculerTotaux.calculerValeurObjectif(solutionTemp, optimizeParameters);
						if (valeurSolutionTemp > valeurSolution) {
							valeurSolution = valeurSolutionTemp;
							bestSolution = solutionTemp;
						}
					}
				}
			}
		}



		return bestSolution;
	}
}
