package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.Toast;
import com.parse.ParseUser;

import java.util.List;


/**
 * Created by michael on 3/8/2014
 */

/**
 * The android activity that has fields for the start date, end date, and space to display the
 * generated financial report/account record.
 */
public class AccountRecordActivity extends Activity{
    private TextView _startDateField, _endDateField, _builtRecordView;
    private ListView _accountBalanceList;
    private TextView _totalBalance;

    private List <UserAccount> _accountList;// = (List<UserAccount>) ParseSingleton.getInstance().get("accountsList");
    private UserAccount _account;// = _accountList.get(v.getInt("accountID"));
    Switch s;


    //private UserAccount _userAccount;

    /**
     * Tell device to create the view based on savedInstanceState and
     * the activity_account_record layout in R file.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_record);

        Bundle extras = getIntent().getExtras();
        /*if (extras != null) {

            String toAdd = extras.getString("date");

            if (extras.getInt("date_type") == 0) {

                _startDateField.setText(toAdd);
            } else {

                _endDateField.setText(toAdd);
            }
        }*/

        _accountList = (List<UserAccount>) ParseSingleton.getInstance().get("accountsList");
        _account = _accountList.get(extras.getInt("accountID"));
        s = (Switch) findViewById(R.id.switch1);
        s.setTextOn("Main Account");
        s.setTextOff(_account.getAccountName());



    }

    /**
     * Generate a pop-up menu based on R.menu.accountsmenu on click of the menu button
     *
     * @param menu describes the menu
     * @return true when menu is created.
     */
    // TODO Customize a menu for the AccountViewActivity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scroll_test, menu);
        return true;
    }

    /**
     * Handle changing activities/views when a menu button is clicked.
     *
     * @param item is the menu item
     * @return true when menu item is clicked.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * On click of the button, input dates checked to be valid.
     * If valid, an accountRecord is generated and displayed.
     * Else, program toasts user that "Invalid Date Inputs" were used.
     *
     * @param V the button clicked.
     */
    public void onButtonClick(View V) {
        _startDateField = (TextView) findViewById(R.id.editText_start_date);
        _endDateField = (TextView) findViewById(R.id.editText_end_date);
        String startDateString = _startDateField.getText().toString();
        String endDateString = _endDateField.getText().toString();
         //Find a way to force slashes (our format)

        if (!DateValidator.validateDate(startDateString, endDateString)) {
            Toast.makeText(this, "Invalid Date Inputs", Toast.LENGTH_SHORT).show();
            return;
        }

       long x = DateValidator.getFirstDate().getTime();


        AccountRecord report = new AccountRecord(DateValidator.getFirstDate(), DateValidator.getSecondDate(), _account);
        //Toast.makeText(this, report.buildRecord(), Toast.LENGTH_SHORT).show();
        /*_builtRecordView = (TextView) findViewById(R.id.built_record_label);
        _builtRecordView.setText(report.buildRecord());
        _builtRecordView.setMovementMethod(new ScrollingMovementMethod());*/

        String out = "";




        out = (s.isChecked()) ? report.buildRecord() : report.buildSubRecord();



        Intent showRecord = new Intent(this, AccountRecordShow.class);
        showRecord.putExtra("recordString", out);
        startActivity(showRecord);
    }

   /* public void setStart(View v) {

        Intent i = new Intent(getApplicationContext(), StartEnd.class);
        i.putExtra("date_type",0);
        startActivity(i);
    }

    public void setEnd(View v) {

        Intent i = new Intent(getApplicationContext(), StartEnd.class);
        i.putExtra("date_type",1);
        startActivity(i);
    }*/


}
