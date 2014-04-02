package com.example.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Activity in which user can create a new account to track finances.
 */
public class AddAccountDialog extends Activity {
	private EditText _newAccountName;
    private EditText _newAccountValue;

    /**
     * Tell device to create the view based on savedInstanceState and R.activity_addaccount.
     * Displays fields for account name and initial account value.
     *
     * @param savedInstanceState
     */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addaccount);
		
		getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		_newAccountName = (EditText) findViewById(R.id.new_account_name);
        _newAccountValue = (EditText) findViewById(R.id.account_value);
	}

    /**
     * If inputs are valid, creates a new account and stores the information in the (parse) database.
     * Otherwise, toasts that inputs were invalid.
     *
     * @param v the button clicked, indicating completion of inputting information.
     */
	public void createNewAccount(View v) {
        Intent i = new Intent();

		if (InputValidator.isValid(_newAccountName.getText().toString(), _newAccountValue.getText().toString())){
			UserAccount uac = new UserAccount();
			uac.setACL(new ParseACL(ParseUser.getCurrentUser()));
			uac.setUser(ParseUser.getCurrentUser());
			uac.setAccountName(_newAccountName.getText().toString());
            uac.setInitialValue(Double.parseDouble(_newAccountValue.getText().toString()));
            uac.setAccountValue(uac.getInitialValue());
			uac.saveEventually();

            i.putExtra("accountName", _newAccountName.getText().toString());
            i.putExtra("accountValue", Double.parseDouble(_newAccountValue.getText().toString()));
            ParseSingleton.getInstance().put("newAccount", uac);
            setResult(RESULT_OK, i);
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "Invalid Inputs";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            setResult(RESULT_CANCELED, i);
        }

		finish();
	}
}
