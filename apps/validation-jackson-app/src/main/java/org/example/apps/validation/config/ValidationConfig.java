package org.example.apps.validation.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author rival
 * @since 2023-11-08
 */
@Configuration
public class ValidationConfig {

    @Bean
    public LocalValidatorFactoryBean beanValidator(MessageSource messageSource){
        var bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }
}
