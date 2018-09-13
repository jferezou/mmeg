package com.mmeg.glyphes.optimizer.config.cxf;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.http.HTTPConduit;

import java.util.Collections;

import static com.mmeg.glyphes.optimizer.config.cxf.CxfUtils.getHttpConduit;
import static com.mmeg.glyphes.optimizer.config.cxf.CxfUtils.getRestConfig;


/**
 * Un builder pour configurer un client JAXRS
 * 
 * @author Romain GERVAIS
 * 
 * @param <T>
 *            L'interface qui sert de client
 */
public class RestClientConfigBuilder<T> {
	/**
	 * La limite en mémoire des flux à logger avant de créer un fichier temporaire. Pour changer le répertoire où sont créés les fichiers temporaires
	 * il faut ajouter l'option Java -Dorg.apache.cxf.io.CachedOutputStream.OutputDirectory="/mon_repertoire"
	 */
	private static final int LOGGING_IN_MEMORY_LIMIT = 1000 * 1024; // 1Mo

	/** Le client CXF */
	private T client;
	/** La configuration associée au client */
	private ClientConfiguration config;
	/** Le conduit utilisé par le client */
	private HTTPConduit conduit;

	/**
	 * Constructeur
	 */
	private RestClientConfigBuilder() {
		super();
	}

	/**
	 * Instancie un nouveau builder pour configurer un client
	 * 
	 * @param aRestClientBuilder
	 *            un builder d'un client REST
	 * @return un builder pour configurer le client REST
	 */
	public static <T> RestClientConfigBuilder<T> aRestClient(final RestClientBuilder<T> aRestClientBuilder) {
		RestClientConfigBuilder<T> restClientConfig = new RestClientConfigBuilder<>();
		restClientConfig.client = aRestClientBuilder.build();
		restClientConfig.config = getRestConfig(restClientConfig.client);
		restClientConfig.conduit = getHttpConduit(restClientConfig.config);
		return restClientConfig;
	}



	/**
	 * Set un timeout de lecture
	 * 
	 * @param readTimeoutInSeconds
	 *            le temps en secondes du timeout de lecture
	 * @return instance du builder
	 */
	public RestClientConfigBuilder<T> withReadTimeout(final int readTimeoutInSeconds) {
		long readTimeoutInMilli = readTimeoutInSeconds * 1000L;
		conduit.getClient().setReceiveTimeout(readTimeoutInMilli);
		return this;
	}

	/**
	 * Fonction permetant de tracer les entrées/sorties du service concerné.
	 * 
	 * @param activate
	 *            true pour activer les log, false sinon
	 * @return l'instance du builder
	 */
	public RestClientConfigBuilder<T> withLog(final boolean activate) {
		if (activate) {
			config.getEndpoint().getInInterceptors().add(getLoggingInInterceptor());
			config.getEndpoint().getOutInterceptors().add(getLoggingOutInterceptor());
		}
		return this;
	}

	/**
	 * Configuration de l'envoi ou non du body par chunk
	 * @param chunkedAllowed Envoie par chunk ou non
	 * @return l'instance du builder
	 */
	public RestClientConfigBuilder<T> withChunked(final boolean chunkedAllowed) {
		conduit.getClient().setAllowChunking(chunkedAllowed);
		return this;
	}

	/**
	 * Créer un LoggingInInterceptor
	 * 
	 * @return LoggingInInterceptor
	 */
	protected LoggingInInterceptor getLoggingInInterceptor() {
		LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
		loggingInInterceptor.setInMemThreshold(LOGGING_IN_MEMORY_LIMIT);
		return loggingInInterceptor;
	}

	/**
	 * Créer un LoggingOutInterceptor
	 * 
	 * @return LoggingOutInterceptor
	 */
	protected LoggingOutInterceptor getLoggingOutInterceptor() {
		LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
		loggingOutInterceptor.setInMemThreshold(LOGGING_IN_MEMORY_LIMIT);
		return loggingOutInterceptor;
	}

	/**
	 * @return le client configuré avec toutes les optins spéicifiées lors de la construction
	 */
	public T build() {
		return client;
	}
}
