package com.example.m3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Login extends Activity {

	private RegistrationAdapter regDBAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void loginUser(View v) {

		EditText name = (EditText) findViewById(R.id.editText1);
		EditText pass = (EditText) findViewById(R.id.editText2);
		
		String currentName = name.getText().toString();
		String currentPass = pass.getText().toString();
		
		String potentialPass = regDBAdapter.getSingleEntry(currentName);
		
		if ((currentName.equals("admin") && currentPass.toString().equals("pass123")) || (currentPass.equals(potentialPass))) {
    	
		Intent buttonIntent = new Intent(Login.this, UserMain.class);
    	Login.this.startActivity(buttonIntent);
		
		}
		
		else {
			
			
			AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

			dlgAlert.setMessage("Incorrect Username or Password");
			dlgAlert.setTitle("Login Failed");
			dlgAlert.setPositiveButton("OK", null);
			dlgAlert.setCancelable(true);
			dlgAlert.create().show();
			
		}
		
		
	}

}
