package com.tech.atm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MvcConfig {
    @Bean(name = "messageSource")
    public ResourceBundleMessageSource getMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages/applicationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        // messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
}
