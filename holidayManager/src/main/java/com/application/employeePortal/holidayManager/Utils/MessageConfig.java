package com.application.employeePortal.holidayManager.Utils;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;


/**
 *  Bean configuration for Resource bundle
 */
@Configuration
public class MessageConfig {

    /**
     * Load property files and provide an instance of MessageResource
     * @return
     */
    @Bean("riskLabelResource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource= new ReloadableResourceBundleMessageSource();
        messageSource.addBasenames("classpath:errorMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}