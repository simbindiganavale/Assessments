package com.application.employeePortal.holidayManager.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import java.util.Locale;

/**
 * Utility class to serve labels and static texts
 */
@Component
public class TextProvider {


    @Autowired
    @Qualifier("riskLabelResource")
    MessageSource messageSource;



    /**
     * Get validation error/warning/info messages
     * @param key
     * @return
     */
    public String getValidationMessage(String key){
        return messageSource.getMessage(key, null, Locale.FRENCH);
    }

}