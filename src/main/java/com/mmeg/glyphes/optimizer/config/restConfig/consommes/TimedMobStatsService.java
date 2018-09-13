package com.mmeg.glyphes.optimizer.config.restConfig.consommes;


import com.mmeg.glyphes.optimizer.annotation.LogCall;
import com.mmeg.glyphes.optimizer.pojo.servicesConsommes.MobStats;

/**
 * Proxy qui trace les appels PRR
 * 
 * @author Romain GERVAIS
 * 
 */
public class TimedMobStatsService implements MobStatsService {

	/** l'instance à qui déléguer l'appel */
	private MobStatsService delegate;

	/**
	 * Constructeur pour faire de l'introspection
	 */
	TimedMobStatsService() {
		super();
	}

	/**
	 * Constructeur
	 * 
	 * @param delegate
	 *            l'instance à qui déléguer l'appel
	 */
	public TimedMobStatsService(final MobStatsService delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	@LogCall
	public MobStats getStatistiques(final String numeroTrain, final String dateCirculation) {
		return delegate.getStatistiques(numeroTrain, dateCirculation);
	}

}
