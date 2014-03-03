package com.example.app;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.math.BigDecimal;
import java.util.Date;

@ParseClassName("Transaction")
public class Transaction extends ParseObject {
	public Transaction() {
		setTransactionValue(0.0);
        setDate(new Date());
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
	
	public void setTransactionValue(double d) {
		put("money", new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTransactionValue() {
		return getDouble("money");
	}
	
	public void setDate(Date d) {
		put("date", d);
	}
	
	public Date getDate() {
		return getDate("date");
	}
}
