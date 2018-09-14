package com.mmeg.glyphes.optimizer.service.impl;

import com.mmeg.glyphes.optimizer.config.restConfig.consommes.MmegDbService;
import com.mmeg.glyphes.optimizer.pojo.Mob;
import com.mmeg.glyphes.optimizer.pojo.OptimizeParameters;
import com.mmeg.glyphes.optimizer.pojo.Solution;
import com.mmeg.glyphes.optimizer.pojo.servicesConsommes.mmegdb.MobStats;
import com.mmeg.glyphes.optimizer.service.*;
import com.mmeg.glyphes.optimizer.utils.EmplacementGlyphesEnum;
import com.mmeg.glyphes.optimizer.utils.SetEnum;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;

@Service
public class OptimizeServiceImpl implements OptimizeService {

    @Resource
    private MmegDbService mmegDbService;

    @Resource
    private MagasinGlyphesService magasinGlyphesService;
    @Resource
    private VerificationContrainte verificationContrainte;

    @Resource
    private VoisinnageService voisinnageService;

    @Value("${listeTabou.taille}")
    private int queueSize;

    @Value("${iterations:200000}")
    private int iterations;

    private static final Logger LOGGER = LoggerFactory.getLogger(OptimizeServiceImpl.class);

    @Override
    public void optimize(final OptimizeParameters optimizeParameters) {
        optimizeParameters.convertSet();
        MobStats mobStats = mmegDbService.getStatistiques(optimizeParameters.getNomMob(),optimizeParameters.getElementMob().getCode());
        Mob mob = Mob.of(mobStats);
        LOGGER.info("Optimisation du monstre : {}", mob);

        gererPoolRunes(optimizeParameters.getPremierSet(), optimizeParameters.getSecondSet());

        List<Solution> solutionsTrouvees = new ArrayList<>();
        Queue<Solution> tabouList = new CircularFifoQueue<>(queueSize);
        Solution solution = getPremiereSolution();
        tabouList.add(solution);

        int i = 0;
        while (i < iterations) {
            final Solution solutionTemp = optimizeSolution(solution, optimizeParameters, tabouList);
            tabouList.add(solutionTemp);

            if(verificationContrainte.verifierContraintes(solutionTemp, optimizeParameters)) {
                solutionsTrouvees.add(solutionTemp);
            }
            i++;
        }

        solutionsTrouvees.sort(Comparator.comparing(Solution::getValeurFonctionObjectif));
        LOGGER.info("Solutions trouvées : \n{}", solutionsTrouvees);
    }


    protected Solution optimizeSolution(Solution solution, final OptimizeParameters optimizeParameters, final Queue<Solution> tabouList) {

        Future<Solution> s1 = voisinnageService.parcourirVoisinageAsync(solution, EmplacementGlyphesEnum.CARRE1, optimizeParameters, tabouList);
        Future<Solution> s2 = voisinnageService.parcourirVoisinageAsync(solution, EmplacementGlyphesEnum.CARRE2, optimizeParameters, tabouList);
        Future<Solution> s3 = voisinnageService.parcourirVoisinageAsync(solution, EmplacementGlyphesEnum.ROND1, optimizeParameters, tabouList);
        Future<Solution> s4 = voisinnageService.parcourirVoisinageAsync(solution, EmplacementGlyphesEnum.ROND2, optimizeParameters, tabouList);
        Future<Solution> s5 = voisinnageService.parcourirVoisinageAsync(solution, EmplacementGlyphesEnum.HEXA1, optimizeParameters, tabouList);
        Future<Solution> s6 = voisinnageService.parcourirVoisinageAsync(solution, EmplacementGlyphesEnum.HEXA2, optimizeParameters, tabouList);

        Solution solutionRetenue = Arrays.asList(s1,s2,s3,s4,s5,s6).stream().map(sol -> {
            try {
                return sol.get();
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.error("oups", e);
            }
            return null;
        })
                .max(Comparator.comparing(Solution::getValeurFonctionObjectif))
                .orElseThrow(NoSuchElementException::new);


        return solutionRetenue;
    }

    protected Solution getPremiereSolution() {


        return null;
    }

    protected void gererPoolRunes(final SetEnum premierSet, final SetEnum secondSet){

    }
}
