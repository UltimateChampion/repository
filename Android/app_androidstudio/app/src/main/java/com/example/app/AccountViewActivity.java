package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by richard on 3/3/14.
 */
public class AccountViewActivity extends Activity implements OnItemClickListener {
    private TextView _accountNameField;
    private ListView _txnList;
    private TextView _accountBalanceField;
    private TextView _numTransactionsField;
    private TransactionAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_view);

        _adapter = new TransactionAdapter(this, new ArrayList<Transaction>());

        _accountNameField = (TextView) findViewById(R.id.accountview_account_label);
        _txnList = (ListView) findViewById(R.id.accountview_txn_list);
        _accountBalanceField = (TextView) findViewById(R.id.accountview_balance);
        _numTransactionsField = (TextView) findViewById(R.id.accountview_num_txns);

        _txnList.setAdapter(_adapter);
        _txnList.setOnItemClickListener(this);

        Bundle v = getIntent().getExtras();
        if (v == null) return;

        String accountName = v.getString("accountName");
        double balance = v.getDouble("accountValue");
        String accountBalance = String.format("$%.2f", balance);

        _accountNameField.setText(accountName);
        _accountBalanceField.setText(accountBalance);
        _numTransactionsField.setText("" + _adapter.getCount());

        if (balance < 0.0) _accountBalanceField.setTextColor(Color.RED);

        updateData();
    }

    // TODO this
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    // TODO Customize a menu for the AccountViewActivity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.accountsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addaccount:
                // startActivityForResult(new Intent(this, AddAccountDialog.class), 1);
                // updateData();
                return true;
            case R.id.addtransaction:
                addTransaction();
                return true;
            case R.id.refresh:
                updateData();
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

    public void updateData() {
        ParseQuery<Transaction> query = ParseQuery.getQuery(Transaction.class);
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
        query.findInBackground(new FindCallback<Transaction>() {
            @Override
            public void done(List<Transaction> txns, ParseException e) {
                if (e != null) {
                    Log.e(getClass().getName(), "error code: " + e.getCode());
                    return;
                }

                // TODO fix ordering of Transactions in _txnList after updating
                if (txns != null) {
                    _adapter.clear();
                    for (int i = 0; i < txns.size(); i++) {
                        _adapter.add(txns.get(i));
                    }
                }
                else Log.i(this.getClass().getName(), "NO TRANSACTIONS!");
            }
        });
    }

    // TODO Implement the onItemClickListener
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    private void addTransaction() {
        Intent intent = new Intent(this, TransactionActivity.class);
        intent.putExtra("accountName", _accountNameField.getText().toString());
        intent.putExtra("edit", true);
        startActivityForResult(intent, 1);
    }
}
