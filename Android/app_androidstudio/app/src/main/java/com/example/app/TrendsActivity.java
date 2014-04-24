package com.example.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import java.util.Date;
import java.util.List;

public class TrendsActivity extends ActionBarActivity {

    private List<UserAccount> _accountList;
    private UserAccount _account;
    private int _accountID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends);

        Bundle extras = getIntent().getExtras();
        _accountList = (List<UserAccount>) ParseSingleton.getInstance().get("accountsList");
        _account = _accountList.get(extras.getInt("accountID"));
        _accountID = extras.getInt("accountID");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.trends, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_trends, container, false);
            return rootView;
        }
    }

    public void goChart(View v) {

        Intent chart = new Intent(this, ExpensePlotActivity.class);
        chart.putExtra("accountID", _accountID);
        startActivity(chart);

    }

    public void goReport(View v) {

        AccountRecord report = new AccountRecord(new Date(0), new Date(), _account);

        String out = report.buildTrendRecord();

        Intent showRecord = new Intent(this, AccountRecordShow.class);
        showRecord.putExtra("recordString", out);
        startActivity(showRecord);

    }

    public void goBack(View v) {

        Intent showRecord = new Intent(this, UserAccountsActivity.class);
        startActivity(showRecord);


    }

}
