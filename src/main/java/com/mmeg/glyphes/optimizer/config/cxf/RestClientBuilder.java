package com.mmeg.glyphes.optimizer.config.cxf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;

/**
 * Un builder pour construire un client Rest
 * 
 * @author Romain GERVAIS
 * 
 * @param <T>
 *            L'interface qui sert de service
 */
public class RestClientBuilder<T> {

	private JAXRSClientFactoryBean restClientFactory;

	/**
	 * Constuit un builder de base
	 */
	private RestClientBuilder() {
		super();
	}

	/**
	 * Constuit un client REST JSON de base (pas d'authentification, pas d'options particulières)
	 * 
	 * @param serviceClass
	 *            interface du client
	 * @param url
	 *            l'url d'appelle de base du service
	 */
	private RestClientBuilder(final Class<T> serviceClass, final String url) {
		restClientFactory = newJAXRSClientFactoryBean(serviceClass, url);
		restClientFactory.setProvider(new JacksonJsonProvider());
	}

	/**
	 * Constuit un client REST XML de base (pas d'authentification, pas d'options particulières)
	 * 
	 * @param serviceClass
	 *            la classe du client REST
	 * @param url
	 *            l'url d'appelle de base du service
	 * @param additionalClasses
	 *            Des classes aditionnelles qui ne sont pas détectées automatiquement par JAXB (classes génériques)
	 */
	private RestClientBuilder(final Class<T> serviceClass, final String url, final Class<?>... additionalClasses) {
		restClientFactory = newJAXRSClientFactoryBean(serviceClass, url);

		JAXBElementProvider<Object> provider = new JAXBElementProvider<>();
		if (ArrayUtils.isNotEmpty(additionalClasses)) {
			provider.setExtraClass(additionalClasses);
		}

		restClientFactory.setProvider(provider);
	}


	/**
	 * Ajoute un ou plusieurs providers au système CXF pour ce client.
	 *
	 * @param providers
	 * @return
	 */
	public RestClientBuilder<T> usingProviders(Object... providers) {
		for (Object provider : providers) {
			restClientFactory.setProvider(provider);
		}
		return this;
	}

	/**
	 * Construit une factory de base pour un client REST
	 * 
	 * @param serviceClass
	 *            la classe du service REST (ou interface)
	 * @param url
	 *            l'url de base du client REST
	 * @return la factory pour créer un client REST
	 */
	private JAXRSClientFactoryBean newJAXRSClientFactoryBean(final Class<T> serviceClass, final String url) {
		JAXRSClientFactoryBean factory = new JAXRSClientFactoryBean();
		factory.setServiceClass(serviceClass);
		factory.setAddress(url);
		return factory;
	}

	/**
	 * Instancie un builder pour un client REST de type JSON
	 * 
	 * @param serviceClass
	 *            la classe du client REST
	 * @param url
	 *            l'url d'appelle de base du service
	 * @return instance du builder
	 */
	public static <T> RestClientBuilder<T> ofJsonType(final Class<T> serviceClass, final String url) {
		return new RestClientBuilder<>(serviceClass, url);
	}

	/**
	 * Instancie un builder pour un client REST de type XML
	 * 
	 * @param servicecClass
	 *            la classe du client REST
	 * @param url
	 *            l'url d'appelle de base du service
	 * @param additionnalClasses
	 *            classe aditionnelle pour le marshalling dans JAXB. Dans le doute laisser à vide. Si jamais une erreur survient à l'exécution ajouter
	 *            les classes manquantes.
	 * @return instance du builder
	 */
	public static <T> RestClientBuilder<T> ofXmlType(final Class<T> servicecClass, final String url, final Class<?>... additionnalClasses) {
		return new RestClientBuilder<>(servicecClass, url, additionnalClasses);
	}

	/**
	 * Instancie un builder pour un client REST sans particularité (ni JSON, ni XML). Ces propriétés doivent être triatées par la suite.
	 * 
	 * @param serviceClass
	 *            la classe du client REST
	 * @param url
	 *            l'url d'appelle de base du service
	 * @return instance du builder
	 */
	public static <T> RestClientBuilder<T> ofClass(final Class<T> serviceClass, final String url) {
		RestClientBuilder<T> restClientBuilder = new RestClientBuilder<>();
		restClientBuilder.restClientFactory = restClientBuilder.newJAXRSClientFactoryBean(serviceClass, url);
		return restClientBuilder;
	}

	/**
	 * Ajoute un JacksonJsonProvider au client REST
	 * 
	 * @param objectMapper
	 *            l'object mapper sur lequel doit s'appuyer le jackson provider
	 * @return le builder
	 */
	public RestClientBuilder<T> withJsonMapper(ObjectMapper objectMapper) {
		restClientFactory.setProvider(new JacksonJsonProvider(objectMapper));
		return this;
	}

	/**
	 * Indique que le client doit être threadsafe car réutilisé dans plusieurs threads. La documentation CXF n'est pas très claire à ce sujet, pour
	 * savoir si c'est necessaire ou non d'appliquer ce paramètre. Dans un contexte Spring où les clients sont de scope singleton il est préférable de
	 * ne pas prendre de risque.
	 * 
	 * @param threadSafe
	 *            true pour indiquer que le service est threadsafe, false sinon
	 * @return instance du builder
	 */
	public RestClientBuilder<T> threadSafe() {
		restClientFactory.setThreadSafe(true);
		return this;
	}

	/**
	 * Construit l'instance du client REST
	 * 
	 * @return l'instance du client
	 */
	@SuppressWarnings("unchecked")
	public T build() {
		return (T) restClientFactory.create();
	}
}