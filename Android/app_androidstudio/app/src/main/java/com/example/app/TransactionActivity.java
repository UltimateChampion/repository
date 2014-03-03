package com.example.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by richard on 3/3/14.
 */
public class TransactionActivity extends Activity {
    private ArrayList<EditText> editTexts;


    // TODO Clear text in txn name field on first tap on first create only
    // (or just highlight all of the text on selecting the EditText every time


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
    }
}
