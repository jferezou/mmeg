package com.mmeg.glyphes.optimizer.exception;

/**
 * Une exception à renvoyer dans les services. Elle expose que les données necessaires. // TODO renvoyer le code de l'erreur fonctionnelle dans les
 * services.
 * 
 * @author Romain GERVAIS
 * 
 */
public class ServiceException implements ApplicationException {

	private String exceptionId;

	private String message;

	/**
	 * Constructeur pour les librairies de parsing, à ne pas utiliser dans notre code
	 */
	ServiceException() {

	}

	/**
	 * Constructeur
	 * 
	 * @param e
	 */
	public ServiceException(ApplicationException e) {
		super();
		this.exceptionId = e.getId();
		this.message = e.getMessage();
	}

	@Override
	public String getId() {
		return exceptionId;
	}

	@Override
	public void setId(String id) {
		exceptionId = id;
	}

	@Override
	public boolean hasId() {
		return exceptionId != null;
	}

	@Override
	public boolean hasNoId() {
		return !hasId();
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exceptionId == null) ? 0 : exceptionId.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceException other = (ServiceException) obj;
		if (exceptionId == null) {
			if (other.exceptionId != null)
				return false;
		} else if (!exceptionId.equals(other.exceptionId))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
}
