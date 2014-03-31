package com.example.app;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by AmierNaji on 3/30/14.
 */
public class DateValidator {

    private static Date startDate;
    private static Date endDate;
    private static SimpleDateFormat dateFormatter;

    public static Date getFirstDate() {
        return startDate;
    }

    public static Date getSecondDate() {
        return endDate;
    }

    public static boolean validateDate(String a, String b) {

        dateFormatter = new SimpleDateFormat("MM/dd/yy");

        Date startDate = null, endDate = null;
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
