package com.mmeg.glyphes.optimizer.service.impl;

import com.mmeg.glyphes.optimizer.config.restConfig.consommes.MobStatsService;
import com.mmeg.glyphes.optimizer.pojo.OptimizeParameters;
import com.mmeg.glyphes.optimizer.pojo.servicesConsommes.MobStats;
import com.mmeg.glyphes.optimizer.service.OptimizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OptimizeServiceImpl implements OptimizeService {

    @Resource
    private MobStatsService mobStatsService;


    private static final Logger LOGGER = LoggerFactory.getLogger(OptimizeServiceImpl.class);

    @Override
    public void optimize(final OptimizeParameters optimizeParameters) {
        MobStats mobStats = mobStatsService.getStatistiques(optimizeParameters.getNomMob(),optimizeParameters.getElementMob());
        LOGGER.info(mobStats.toString());
    }
}
