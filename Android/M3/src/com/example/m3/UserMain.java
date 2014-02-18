package com.example.m3;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class UserMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_main);
		
		String name = "";
		Bundle extras = getIntent().getExtras();
		
			
		    name = extras.getString("realName");
		
		
		TextView successful = (TextView) findViewById(R.id.successScenario);
		successful.setText("SUCCESS! Welcome "+name);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_main, menu);
		return true;
	}
	
	
}
