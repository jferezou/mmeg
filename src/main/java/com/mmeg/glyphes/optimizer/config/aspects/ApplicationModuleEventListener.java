package com.mmeg.glyphes.optimizer.config.aspects;


import com.mmeg.glyphes.optimizer.annotation.ServiceMethod;

/**
 * Listener associé aux évènements levé par un {@link ApplicationModule}
 */
public interface ApplicationModuleEventListener {
    /**
     * Avant l'invocation d'une méthode de service {@link ServiceMethod}.
     *
     * @param context
     *            le contexte courant d'exécution.
     */
    void beforeServiceMethodExecution(ApplicationModule.Context context);

    /**
     * Après l'invocation d'une méthode de service {@link ServiceMethod}.
     *
     * @param context
     *            le contexte courant d'exécution.
     */
    void afterServiceMethodExecution(ApplicationModule.Context context);
}
