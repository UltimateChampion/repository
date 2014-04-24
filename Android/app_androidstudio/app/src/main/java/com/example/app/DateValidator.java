package com.example.app;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by AmierNaji on 3/30/14.
 */
public class DateValidator {

    private static Date startDate;
    private static Date endDate;
    private static SimpleDateFormat dateFormatter;

    /**
     * Getter for the start date.
     * @return The start date.
     */
    public static Date getFirstDate() {
        return
                new Date(startDate.getTime());
    }

    /**
     * Getter for the end date.
     * @return The end date.
     */
    public static Date getSecondDate() {
        return new Date(endDate.getTime());
    }

    /**
     * Checks for valid Dates.
     * 
     * @param a String representation of the start date.
     * @param b String representation of the end date.
     * @return whether or not the date is valid.
     */
    public static boolean validateDate(String a, String b) {

        dateFormatter = new SimpleDateFormat("MM/dd/yy");

        try {
            startDate = dateFormatter.parse(a);
        } catch (java.text.ParseException e) {

            return false;
        }
        try {

            endDate = dateFormatter.parse(b);
        } catch (java.text.ParseException e) {

            return false;
        }

        if (endDate.compareTo(startDate) <= 0) {
            return false;
        }

        return true;

    }

}
