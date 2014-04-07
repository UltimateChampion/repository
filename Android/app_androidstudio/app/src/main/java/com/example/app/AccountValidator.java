package com.example.app;

/**
 * Created by harrisongalloway on 3/30/14.
 */
public class AccountValidator {
    /**
     * Check for valid account inputs
     *
     * @return true for valid inputs, otherwise false
     * @param username String representing a username
     * @param password String representing a password
     */
    public static boolean isValid(String username, String password) {
        return (isValidUsername(username) && isValidPassword(password));
    }

    public static boolean isValidUsername(String username)
    {
        if (username.length() < 6){
            return false;
        }

        int len = username.length();

        for(int i=0 ; i < len ; i++) {
            char c = username.charAt(i);

            // Make sure there is at least 1 letter

            if (('a'<=c && c<='z')||
                ('A'<=c && c<='Z')){
                return true;
            }
        }
        return false;
    }

    public static boolean isValidPassword(String password)
    {

        if (password.length() < 6){
            return false;
        }



        int len = password.length();

        for(int i=0 ; i < len ; i++) {
            char c = password.charAt(i);

            // Make sure there is at least 1 number

            if ('0'<=c && c<='9') {
                for(int j=0 ; j < len ; j++) {
                    char d = password.charAt(j);

                    // Make sure there is at least 1 capital letter

                    if ('A'<=d && d<='Z'){
                        return true;
                    }
                }
            }

        }
        return false;
   }

}
