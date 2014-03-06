package com.example.app;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.math.BigDecimal;
import java.util.Date;

@ParseClassName("Transaction")
public class Transaction extends ParseObject {
	public Transaction() {
        super();
		// setTransactionValue(0.0);
        // setTransactionDate(new Date());
	}
	
	public void setUser(ParseUser user) {
		put("user", user);
	}

    public void setTransactionName(String s) {
        put("name", s);
    }

    public String getTransactionName() {
        return getString("name");
    }

    public void setTransactionAccount(UserAccount a) {
        put("userAccount", a);
    }

    public UserAccount getTransactionAccount() {
        return (UserAccount)get("userAccount");
    }
	
	public void setTransactionValue(double d) {
		put("value", new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTransactionValue() {
		return getDouble("value");
	}
	
	public void setTransactionDate(Date d) {
		put("date", d);
	}
	
	public Date getTransactionDate() {
		return getDate("date");
	}

    public void setDescription(String s) {
        put("description", s);
    }

    public String getDescription() {
        return getString("description");
    }
}
