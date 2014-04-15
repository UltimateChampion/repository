package com.example.app;

/**
 * Created by michaelfalk on 3/30/14.
 */
public class InputValidator {
    /**
     * Check for valid inputs
     *
     * @return true for valid inputs, otherwise false.
     * @param name String representing a name
     * @param value String representing a numeric (monetary) value
     */
    public static boolean isValid(String name, String value) {
        return (name.length() > 0) && (value.length() > 0) && isNumeric(value);
    }

    /**
     * Checks to see if the given string is a valid value.
     *
     * @param str The String representation of a numeric value.
     * @return true if str is a valid numeric, false otherwise.
     */
    public static boolean isNumeric(String str)
    {
        try {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isValidUsername(String name) {
        if (name.matches("[\\[$&+,:;=?@#|\\]]")) return false;
        else if (name.length() == 0) return false;
        else if (name.length() > 30) return false;

        return true;
    }
}
