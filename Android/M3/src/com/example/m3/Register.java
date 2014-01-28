package com.example.m3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Register extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	public void registerUser(View v) {
		
		EditText name = (EditText) findViewById(R.id.editText1);
		EditText dob = (EditText) findViewById(R.id.editText2);
		EditText user = (EditText) findViewById(R.id.editText3);
		EditText pass = (EditText) findViewById(R.id.editText4);
		
		File f = new File(user.getText().toString()+".txt");
		
		
		/*try {
		PrintWriter pw = new PrintWriter(f);
		pw.print(name.getText().toString() + "\n" + dob.getText().toString() + "\n" + user.getText().toString() + "\n"  + pass.getText().toString());
		pw.close();
		}
		catch(Exception e) {
			
		}*/
		
		try {
		FileOutputStream fOut = openFileOutput(user.getText().toString()+".txt", Context.MODE_PRIVATE);
		OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
		myOutWriter.append("test");
		myOutWriter.close();
		}
		catch (Exception e) {
			
		}
		
		
		
		
	}

}
