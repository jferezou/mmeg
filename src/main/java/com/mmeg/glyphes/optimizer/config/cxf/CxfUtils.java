package com.mmeg.glyphes.optimizer.config.cxf;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;

/**
 * Class utilitaire pour CXF
 * 
 * @author Romain GERVAIS
 * 
 */
public class CxfUtils {

	/**
	 * Indique que l'identification avec le service doit se faire par une Basic-Auth
	 */
	public static final String BASIC_AUTH = "Basic";

	/**
	 * Classe utilitaire
	 */
	private CxfUtils() {
		super();
	}

	/**
	 * 
	 * Récupère la configuration d'un client rest
	 * 
	 * @param proxy
	 *            le client
	 * @return la config du client
	 */
	public static ClientConfiguration getRestConfig(final Object proxy) {
		return WebClient.getConfig(proxy);
	}

	/**
	 * Récupère le conduit HTTP de la confif d'un client
	 * 
	 * @param config
	 *            la config du client
	 * @return le conduit http pour ce service
	 */
	public static HTTPConduit getHttpConduit(final ClientConfiguration config) {
		return (HTTPConduit) config.getConduit();
	}


	/**
	 * Renseigne la basic auth sur un conduit
	 * 
	 * @param conduit
	 *            le conduit à mettre à jour
	 * @param username
	 *            le username
	 * @param password
	 *            le password
	 */
	public static void setBasicAuth(final HTTPConduit conduit, final String username, final String password) {
		AuthorizationPolicy authorization = new AuthorizationPolicy();
		authorization.setUserName(username);
		authorization.setPassword(password);
		authorization.setAuthorizationType(BASIC_AUTH);
		conduit.setAuthorization(authorization);
	}

}
