package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
    }

    /**
     * Generate a pop-up menu based on R.menu.accountsmenu on click of the menu button
     *
     * @param menu
     * @return true when menu is created.
     */
    // TODO Customize a menu for the AccountViewActivity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.accountsmenu, menu);
        return true;
    }

    /**
     * Handle changing activities/views when a menu button is clicked.
     *
     * @param item
     * @return true when menu item is clicked.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addaccount:
                // startActivityForResult(new Intent(this, AddAccountDialog.class), 1);
                // updateData();
                return true;
            case R.id.addtransaction:
//                addTransaction();
                return true;
            case R.id.refresh:
//                updateData();
                return true;
            case R.id.manageAccountMenuItem:
                Log.e(getClass().getName(), "Manage Accounts not implemented yet!");
                return true;
            case R.id.logOutMenuItem:
                ParseUser.logOut();
                startActivity(new Intent(this, LoginActivity.class));
                return true;
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

        if (DateValidator.validateDate(startDateString, endDateString)) {
            Toast.makeText(this, "Invalid Date Inputs", Toast.LENGTH_SHORT).show();
            return;
        }


        AccountRecord report = new AccountRecord(DateValidator.getFirstDate(), DateValidator.getSecondDate());
        //Toast.makeText(this, report.buildRecord(), Toast.LENGTH_SHORT).show();
         _builtRecordView= (TextView) findViewById(R.id.built_record_label);
         _builtRecordView.setText(report.buildRecord());
         _builtRecordView.setMovementMethod(new ScrollingMovementMethod());
    }

}
