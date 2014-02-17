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
import android.widget.Button;
import android.widget.Toast;

public class Register extends Activity {
	
	private EditText name, dob, user, pass, confirmPass;
	private Button createAccountButton;
	
	private RegistrationAdapter regDBAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		// Get References of Views
		name = (EditText) findViewById(R.id.editText1);
		dob = (EditText) findViewById(R.id.editText2);
		user = (EditText) findViewById(R.id.editText3);
		pass = (EditText) findViewById(R.id.editText4);
		confirmPass = (EditText) findViewById(R.id.editText5);
		
		createAccountButton=(Button)findViewById(R.id.button1);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
 
        public void onClick(View v) {
            // TODO Auto-generated method stub
 
            String userName=user.getText().toString();
            String password=pass.getText().toString();
            String confirmPassword=confirmPass.getText().toString();
 
            // check if any of the fields are vacant
            if(userName.equals("")||password.equals("")||confirmPassword.equals(""))
            {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
            }
            // check if both password matches
            if(!password.equals(confirmPassword)){
                Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                return;
            }
            else{
                // Save the Data in Database
            	System.out.println(regDBAdapter);
            	System.out.println(userName);
            	System.out.println(password);
                regDBAdapter.insertEntry(userName, password);
                Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
            	}
        	}
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	public void registerUser(View v) {
		
		
		
		File f = new File(user.getText().toString()+".txt");
		
		
		/*try {
		PrintWriter pw = new PrintWriter(f);
		pw.print(name.getText().toString() + "\n" + dob.getText().toString() + "\n" + user.getText().toString() + "\n"  + pass.getText().toString());
		pw.close();
		}
		catch(Exception e) {
			
		}*/
		
		try {
			
			PrintWriter pw = new PrintWriter(f);
			pw.print(name.getText().toString() + "\n" + dob.getText().toString() + "\n" + user.getText().toString() + "\n"  + pass.getText().toString());
			pw.close();
		/*FileOutputStream fOut = openFileOutput(user.getText().toString()+".txt", Context.MODE_PRIVATE);
		OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
		myOutWriter.append("test");
		myOutWriter.close();*/
			
			/*String FILENAME = ""+user.getText().toString();
			String string = ""+pass.getText().toString()+"\n"+name.getText().toString();

			FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.write(string.getBytes());
			fos.close();*/
			
		}
		catch (Exception e) {
			
		}
	
		
	}

}
