package com.mmeg.glyphes.optimizer.config.restConfig.consommes;


import com.mmeg.glyphes.optimizer.annotation.LogCall;
import com.mmeg.glyphes.optimizer.pojo.servicesConsommes.mmegdb.MobStats;

/**
 * Proxy qui trace les appels PRR
 * 
 * @author Romain GERVAIS
 * 
 */
public class TimedMmegDbService implements MmegDbService {

	/** l'instance à qui déléguer l'appel */
	private MmegDbService delegate;

	/**
	 * Constructeur pour faire de l'introspection
	 */
	TimedMmegDbService() {
		super();
	}

	/**
	 * Constructeur
	 * 
	 * @param delegate
	 *            l'instance à qui déléguer l'appel
	 */
	public TimedMmegDbService(final MmegDbService delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	@LogCall
	public MobStats getStatistiques(final String numeroTrain, final String dateCirculation) {
		return delegate.getStatistiques(numeroTrain, dateCirculation);
	}

}
