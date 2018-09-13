package com.mmeg.glyphes.optimizer.exception;

	public class TechniqueException extends AbstractApplicationException {

		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 4027150744711819606L;

		/**
		 * Constructeur
		 */
		public TechniqueException() {
			super();
		}

		/**
		 * @param message
		 *            Le message à afficher
		 * @param cause
		 *            L'exception d'origine
		 */
		public TechniqueException(final String message, final Throwable cause) {
			super(message, cause);
		}

		/**
		 * @param message
		 *            Le message à afficher
		 */
		public TechniqueException(final String message) {
			super(message);
		}

		/**
		 * @param cause
		 *            L'exception d'origine
		 */
		public TechniqueException(final Throwable cause) {
			super(cause);
		}

		/**
		 * Wrap dans une technique exception si ce n'en est pas déjà une
		 *
		 * @param cause
		 *            l'erreur à wrapper
		 * @return voir ci-dessus
		 */
		public static TechniqueException wrap(final Throwable cause) {
			TechniqueException techniqueException;
			if (cause instanceof TechniqueException) {
				techniqueException = (TechniqueException) cause;
			} else {
				techniqueException = new TechniqueException(cause.getMessage(), cause);
			}
			return techniqueException;
		}
}
