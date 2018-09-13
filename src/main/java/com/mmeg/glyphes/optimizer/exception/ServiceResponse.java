package com.mmeg.glyphes.optimizer.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe standard pour renvoyer une répon
 * 
 * @author Romain GERVAIS
 * 
 * @param <TypeReponse>
 *            Le type du contenu de la réponse
 */
public class ServiceResponse<TypeReponse> {

	/** Le contenu de la réponse */
	private TypeReponse value;

	private List<FonctionnelleServiceException> messagesErreursFonctionnelles = new ArrayList<>();

	private List<ServiceException> messagesErreursTechniques = new ArrayList<>();

	/**
	 * Constructeur pour les librairies de parsing, à ne pas utiliser dans notre code
	 */
	ServiceResponse() {

	}

	/**
	 * Constructeur
	 * 
	 * @param reponse
	 *            le contenu de la réponse
	 */
	ServiceResponse(TypeReponse reponse) {
		this.value = reponse;
	}

	/**
	 * Static factory
	 * 
	 * @param reponse
	 *            la reponse à encapsuler
	 * @return La réponse encapsulée
	 */
	public static <NouveauType> ServiceResponse<NouveauType> of(NouveauType reponse) {
		return new ServiceResponse<>(reponse);
	}

	/**
	 * Static factory pour créer une nouvelle ServiceResponse
	 * 
	 * @param exception
	 *            L'exception à partir de laquelle créer une réponse
	 * @return La réponse
	 */
	public static ServiceResponse<?> from(TechniqueException exception) {
		ServiceResponse<?> serviceResponse = new ServiceResponse<>(null);
		TechniqueException filterTechException = new TechniqueException("Exception technique "+exception.getId());
		filterTechException.setId(exception.getId());
		serviceResponse.addErreurTechnique(filterTechException);
		return serviceResponse;
	}

	/**
	 * Static factory pour créer une nouvelle ServiceResponse
	 * 
	 * @param exception
	 *            L'exception à partir de laquelle créer une réponse
	 * @return La réponse
	 */
	public static ServiceResponse<?> from(FonctionnelleException exception) {
		ServiceResponse<?> serviceResponse = new ServiceResponse<>(null);
		serviceResponse.addErreurFonctionnelle(exception);
		return serviceResponse;
	}

	/**
	 * Ajouter une erreur fonctionnelle
	 * 
	 * @param exception
	 *            une exception quelconque
	 */
	public void addErreurFonctionnelle(FonctionnelleException exception) {
		getMessagesErreursFonctionnelles().add(new FonctionnelleServiceException(exception));
	}

	/**
	 * Ajouter une erreur technique
	 * 
	 * @param exception
	 *            une exception quelconque
	 */
	public void addErreurTechnique(TechniqueException exception) {
		getMessagesErreursTechniques().add(new ServiceException(exception));
	}

	/**
	 * Retourne le contenu de la réponse
	 * 
	 * @return Le contenu de la réponse
	 */
	public TypeReponse getValue() {
		return value;
	}

	/**
	 * Setter pour les librairies de parsing
	 * 
	 * @param value
	 *            le contenu de la réponse
	 */
	void setValue(TypeReponse value) {
		this.value = value;
	}

	/**
	 * Getter pour récupérer les exceptions fonctionnelles formattées pour les webservices
	 * 
	 * @return Une liste de ServiceException pour renvoyer dans les webservices
	 */
	public List<FonctionnelleServiceException> getMessagesErreursFonctionnelles() {
		return messagesErreursFonctionnelles;
	}

	public void setMessagesErreursFonctionnelles(List<FonctionnelleServiceException> messagesErreursFonctionnelles) {
		this.messagesErreursFonctionnelles = messagesErreursFonctionnelles;
	}

	/**
	 * Getter pour récupérer les exceptions techniques formattées pour les webservices
	 * 
	 * @return Une liste de ServiceException pour renvoyer dans les webservices
	 */
	public List<ServiceException> getMessagesErreursTechniques() {
		return messagesErreursTechniques;
	}

	public void setMessagesErreursTechniques(List<ServiceException> messagesErreursTechniques) {
		this.messagesErreursTechniques = messagesErreursTechniques;
	}
}
