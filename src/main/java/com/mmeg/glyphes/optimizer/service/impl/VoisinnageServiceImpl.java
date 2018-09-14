package com.mmeg.glyphes.optimizer.service.impl;

import com.mmeg.glyphes.optimizer.annotation.LogCall;
import com.mmeg.glyphes.optimizer.pojo.Glyphage;
import com.mmeg.glyphes.optimizer.pojo.Mob;
import com.mmeg.glyphes.optimizer.pojo.OptimizeParameters;
import com.mmeg.glyphes.optimizer.pojo.Solution;
import com.mmeg.glyphes.optimizer.pojo.glyphes.Glyphe;
import com.mmeg.glyphes.optimizer.service.CalculerTotaux;
import com.mmeg.glyphes.optimizer.service.MagasinGlyphesService;
import com.mmeg.glyphes.optimizer.service.VerificationContrainte;
import com.mmeg.glyphes.optimizer.service.VoisinnageService;
import com.mmeg.glyphes.optimizer.utils.EmplacementGlyphesEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.Resource;
import java.util.List;

public class VoisinnageServiceImpl implements VoisinnageService {
	private static final Logger LOGGER = LoggerFactory.getLogger(VoisinnageServiceImpl.class);

	@Resource
	MagasinGlyphesService magasinGlyphesService;

	@Resource
	VerificationContrainte verificationContrainte;


	@Resource
	CalculerTotaux calculerTotaux;

	@Override
	@LogCall
	@Async("asyncVoisinnageExecutor")
	public Solution parcourirVoisinage(final Solution solutionCourante, final EmplacementGlyphesEnum emplacementAChanger, final OptimizeParameters optimizeParameters, final List<Solution> listeTabou) {


		Solution bestSolution = new Solution();
		if(EmplacementGlyphesEnum.CARRE1.equals(emplacementAChanger) || EmplacementGlyphesEnum.CARRE2.equals(emplacementAChanger)) {
			List<Glyphe> listeCarre = magasinGlyphesService.getGlyphesCarre();
			Glyphe glypheUtilise1 = solutionCourante.getGlyphage().getSlotCarre1();
			Glyphe glypheUtilise2 = solutionCourante.getGlyphage().getSlotCarre2();
			bestSolution = voisinnageDirect(listeCarre, glypheUtilise1, glypheUtilise2, solutionCourante.getMob(), solutionCourante.getGlyphage(), optimizeParameters, emplacementAChanger, listeTabou);
		}
		else if(EmplacementGlyphesEnum.ROND1.equals(emplacementAChanger) || EmplacementGlyphesEnum.ROND2.equals(emplacementAChanger)) {
			List<Glyphe> listeRond = magasinGlyphesService.getGlyphesRond();
			Glyphe glypheUtilise1 = solutionCourante.getGlyphage().getSlotRond1();
			Glyphe glypheUtilise2 = solutionCourante.getGlyphage().getSlotRond2();
			bestSolution = voisinnageDirect(listeRond, glypheUtilise1, glypheUtilise2, solutionCourante.getMob(), solutionCourante.getGlyphage(), optimizeParameters, emplacementAChanger, listeTabou);
		}
		else if(EmplacementGlyphesEnum.HEXA1.equals(emplacementAChanger) || EmplacementGlyphesEnum.HEXA2.equals(emplacementAChanger)) {
			List<Glyphe> listeHexa = magasinGlyphesService.getGlyphesHexa();
			Glyphe glypheUtilise1 = solutionCourante.getGlyphage().getSlotHexa1();
			Glyphe glypheUtilise2 = solutionCourante.getGlyphage().getSlotHexa2();
			bestSolution = voisinnageDirect(listeHexa, glypheUtilise1, glypheUtilise2, solutionCourante.getMob(), solutionCourante.getGlyphage(), optimizeParameters, emplacementAChanger, listeTabou);
		}



			return bestSolution;
	}


	private Solution voisinnageDirect(List<Glyphe> listeGlyphesEligibles, Glyphe glypheUtilise1, Glyphe glypheUtilise2, Mob mob, Glyphage glyphageCourant, final OptimizeParameters optimizeParameters, final EmplacementGlyphesEnum emplacementAChanger, final List<Solution> listeTabou) {

		double valeurSolution = 0.0;
		Solution bestSolution = new Solution();
		for(Glyphe nextGlyphe : listeGlyphesEligibles) {
			if(!nextGlyphe.equals(glypheUtilise1) && !nextGlyphe.equals(glypheUtilise2)) {
				Glyphage glyphageTest = new Glyphage(glyphageCourant);

				glyphageTest.changerGlyphe(nextGlyphe, emplacementAChanger);

				Solution solutionTemp = new Solution();
				solutionTemp.setMob(mob);
				solutionTemp.setGlyphage(glyphageTest);
				if(verificationContrainte.verifierContraintesRelachees(solutionTemp, optimizeParameters, 0.66, 2)) {
					calculerTotaux.mettreAjourTotaux(solutionTemp);
					double valeurSolutionTemp = calculerTotaux.calculerValeurObjectif(solutionTemp, optimizeParameters);
					solutionTemp.setValeurFonctionObjectif(valeurSolutionTemp);

					LOGGER.debug("Solution intermédiaire trouvée : {}", solutionTemp);
					if (!listeTabou.contains(solutionTemp) && valeurSolutionTemp > valeurSolution) {
						valeurSolution = valeurSolutionTemp;
						bestSolution = solutionTemp;
					}
				}
			}
		}
		return bestSolution;
	}
}
