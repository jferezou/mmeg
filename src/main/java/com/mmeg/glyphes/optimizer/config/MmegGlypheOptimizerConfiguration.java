package com.mmeg.glyphes.optimizer.config;


import com.mmeg.glyphes.optimizer.annotation.ServiceMethod;
import com.mmeg.glyphes.optimizer.config.aspects.ApplicationModule;
import com.mmeg.glyphes.optimizer.config.aspects.ServiceMethodAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Configuration spécifique au projet métier<br/>
 * <br/>
 *
 * <img src="../../../../../../../../../../parent/resources/doc/SIS Configuration.png" width="600px"/>
 */

@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MmegGlypheOptimizerConfiguration {

    /**
     * Créer une instance de ApplicationModule
     *
     * @return une instance de ApplicationModule
     */
    @Bean
    public ApplicationModule getApplicationModule() {
        return new ApplicationModule("MMEG_Glyphes_Optimizer");
    }


    @Bean
    public Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
    /**
     * Aspect pour la gestion des point d'entrée dans le système.
     *
     * @return aspect.
     * @see ServiceMethod
     */
    @Bean
    public ServiceMethodAspect getServiceMethodAspect() {
        return new ServiceMethodAspect();
    }
}
