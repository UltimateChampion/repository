package com.example.app;

import java.util.ArrayList;
import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("User")
public class User extends ParseObject {
	private ArrayList<UserAccount> accounts;
	
	public User() {
		this.accounts = new ArrayList<UserAccount>();
	}
	
	public ArrayList<UserAccount> getUserAccounts() {
		return accounts;
	}
}
