package com.mmeg.glyphes.optimizer.utils;

/**
 * Interface pour les enum qui représentent un code.
 *
 * @param <T>
 *            Le type du code
 * @author Romain GERVAIS
 */
public interface CodeEnum<T> {

	/**
	 * Le code que représente une enum
	 *
	 * @return le code de l'instance de l'enum
	 */
	T getCode();
}
