package com.example.app;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.math.BigDecimal;
import java.util.Date;

@ParseClassName("Transaction")
public class Transaction extends ParseObject {
    
    /**
     * Constructor
     */
    public Transaction() {
        super();
		// setTransactionValue(0.0);
        // setTransactionDate(new Date());
	}
    
    /**
     * Puts user into the database
     * @param user object representing the current user
     */
	public void setUser(ParseUser user) {
		put("user", user);
	}
    
    /**
     * Puts transaction name in database
     * @param s name of the transaction
     */
    public void setTransactionName(String s) {
        put("name", s);
    }
    
    /**
     * Retrieves transaction name from database
     * @return String of the transaction name
     */
    
    public String getTransactionName() {
        return getString("name");
    }
    
    /**
     * Puts the super-account into database
     * @param a object representing the super-account
     */
    public void setTransactionAccount(UserAccount a) {
        put("userAccount", a);
    }
    
    /**
     * Retrieves transaction super account from database
     * @return transaction super account object
     */
    public UserAccount getTransactionAccount() {
        return (UserAccount)get("userAccount");
    }
    
    
    /**
     * Puts transaction value in database
     * @param d value of the transaction
     */
	public void setTransactionValue(double d) {
		put("value", new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
    
    /**
     * Retrieves transaction value from database
     * @return double of the transaction value
     */
	public double getTransactionValue() {
		return getDouble("value");
	}
    
    /**
     * Puts transaction date in database
     * @param d date of the transaction
     */
	public void setTransactionDate(Date d) {
		put("date", d);
	}
    
    /**
     * Retrieves transaction date from database
     * @return Date form of the transaction date
     */
	public Date getTransactionDate() {
		return getDate("date");
	}
    
    /**
     * Puts transaction description in database
     * @param s description of the transaction
     */
    public void setDescription(String s) {
        put("description", s);
    }
    
    /**
     * Retrieves transaction description from database
     * @return String of the transaction description
     */
    public String getDescription() {
        return getString("description");
    }
}
