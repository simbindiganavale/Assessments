package com.application.employeePortal.holidayManager.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GlobalUtils {
    public static final String DATEFORMAT="dd.MM.yyyy";

    /**
     * Convert string to LocalDate
     * @param date
     * @return
     */
    public static LocalDate parseToLocalDate(String date, String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
       return LocalDate.parse(date, formatter);
    }

    /**
     * Convert string to DateTime
     * @param date
     * @return
     */
    public static LocalDateTime parseToDateTime(String date, String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(date, formatter);
    }


    /**
     * Convert string to Util date.
     * @param date
     * @param pattern
     * @return
    
    public static Date parseToUtilDate(String date, String pattern){
      return LocalDateTime.parse(pattern).parseDateTime(date).toDate();
    } */
}
