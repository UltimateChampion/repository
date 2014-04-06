package com.example.app;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

@ParseClassName("Account")
public class UserAccount extends ParseObject {

    private String initVal = "initialValue";
    private String accVal = "accountValue";
    /**
     * Constructor
     */
    public UserAccount() {
        super();
    }
    
    /**
     * Constructs a UserAccount with an account name.
     * @param accountName the name of the account
     */
    public UserAccount(String accountName) {
        setAccountName(accountName);
    }
    
    /**
     * Puts the account name into the database.
     * @param s the account name
     */
    public void setAccountName(String s) {
        put("accountName", s);
    }
    
    /**
     * Puts the initial value of the account into the database.
     * @param d the initial value of the account
     */
    public void setInitialValue(double d) {
        put(initVal, d);
    }
    
    /**
     * Retrieves the intial value of the account from the database.
     * @return double of the intial value of the account
     */
    public double getInitialValue() {
        return getDouble(initVal);
    }
    
    /**
     * Retrieves the account name from the database.
     * @return String of the account name
     */
    public String getAccountName() {
        return getString("accountName");
    }
    
    /**
     * Puts the current account value in the database.
     * @param d the current account value
     */
    public void setAccountValue(double d) {
        put(accVal, d);
    }
    
    /**
     * Retrieves the current account value from the database.
     * @return double of the current account value
     */
    public double getAccountValue() {
        return getDouble(accVal);
    }
    
    /**
     * Puts the list of transactions in the database.
     * @param txnList the list of transactions
     */
    public void setTxnList(List<Transaction> txnList) {
        put("txnList", txnList);
    }
    
    /**
     * Puts the user into the database.
     * @param user the user
     */
    public void setUser(ParseUser user) {
        put("user", user);
    }
    
    /**
     * Converts the account name to a String.
     * @return String of the account name
     */
    @Override
    public String toString() {
        return getString("accountName");
    }
}
