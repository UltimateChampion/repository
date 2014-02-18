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
import android.widget.Toast;

public class Login extends Activity {

	//private RegistrationAdapter regDBAdapter;

	private DBController dbControl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		dbControl = new DBController(Login.this);
		//regDBAdapter = new RegistrationAdapter(this);
		//regDBAdapter = regDBAdapter.open();
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
		
		dbControl.open();
		String potentialPass = dbControl.getPassword(currentName);//regDBAdapter.getSingleEntry(currentName);
    	String userActualName = dbControl.getActualName(currentName);
		dbControl.close();
		
    	dbControl.open();
		Toast.makeText(getApplicationContext(), dbControl.getData(), Toast.LENGTH_LONG).show();
    	dbControl.close();
    	

		//Toast.makeText(getApplicationContext(), userActualName, Toast.LENGTH_LONG).show();
		
		
		if ((currentName.equals("admin") && currentPass.toString().equals("pass123")) || (currentPass.equals(potentialPass))) {
    	
		Intent buttonIntent = new Intent(Login.this, UserMain.class);
		buttonIntent.putExtra("realName", userActualName);
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
