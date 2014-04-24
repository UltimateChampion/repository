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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by richard on 3/3/14.
 */

/**
 * Android activity to display the name, balance, and (number of) transactions for an account.
 */
public class AccountViewActivity extends Activity implements OnItemClickListener {
    private TextView _accountNameField;
    private ListView _txnList;
    private TextView _accountBalanceField;
    private TextView _numTransactionsField;
    private TransactionAdapter _adapter;
    private UserAccount _account;
    private List<UserAccount> _accountList;

    private String accID = "accountID";
    private String accName = "accountName";
    private String edit = "edit";
    private int _accountIdentifier;

    /**
     * Tell device to create the view based on savedInstanceState and
     * the activity_account_view layout in R file. Initializes values using the
     * TransactionAdapter to display the transactions tied to this account.
     *
     * @param savedInstanceState State of the previous activity.
     */
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
        if (v == null) {
            return;
        }

        _accountList = (List<UserAccount>) ParseSingleton.getInstance().get("accountsList");
        _account = _accountList.get(v.getInt("accountID"));
        _accountIdentifier = v.getInt("accountID");

        if (_account == null) Log.e(getClass().getName(), "_account is null");
        String accountName = _account.getAccountName();
        double balance = _account.getAccountValue();
        String accountBalance = String.format("$%.2f", balance);

        _accountNameField.setText(accountName);
        _accountBalanceField.setText(accountBalance);
        _numTransactionsField.setText("" + _adapter.getCount());

        if (balance < 0.0) {
            _accountBalanceField.setTextColor(Color.RED);
        }
        else {
            _accountBalanceField.setTextColor((Color.BLACK));
        }

        updateData();
        final Button ADD_TRANSACTION_BUTTON = (Button) findViewById(R.id.add_transaction_button);
        ADD_TRANSACTION_BUTTON.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addTransaction();
            }
        });
        final Button ACCOUNT_RECORD_BUTTON = (Button) findViewById(R.id.get_account_record_button);
        ACCOUNT_RECORD_BUTTON.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent record = new Intent(v.getContext(), AccountRecordActivity.class);
                record.putExtra("accountID", _accountIdentifier);
                startActivity(record);
            }
        });
        final Button SPENDING_INFO_BUTTON = (Button) findViewById(R.id.get_trends_info_button);
        SPENDING_INFO_BUTTON.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AccountRecord ar = new AccountRecord(new Date(0), new Date(), _account);

                if (ar.getGraphRecords().size() >= 5) {
                    Intent chart = new Intent(v.getContext(), TrendsActivity.class);
                    chart.putExtra("accountID", _accountIdentifier);
                    startActivity(chart);

                }
                Toast.makeText(getApplicationContext(), "Sub-account must have at least 5 transactions to utilize this function. ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Check to observe that transaction was added, and changes text color to reflect negative and positive transactions.
     *
     * @param requestCode signal to request database.
     * @param resultCode signal that database was changed correctly.
     * @param data information on interaction with database.
     */
    // TODO this
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null && data.getBooleanExtra("transactionModified", false)) {
            Transaction t = (Transaction) ParseSingleton.getInstance().get("newTransaction");
            UserAccount u = t.getTransactionAccount();
            _adapter.add(t);
            _numTransactionsField.setText("" + _adapter.getCount());
            u.setAccountValue(u.getAccountValue() + t.getTransactionValue());
            u.saveEventually();
            _accountBalanceField.setText(String.format("$%.2f", _account.getAccountValue()));
        }

        if (_account.getAccountValue() < 0.0) {
            _accountBalanceField.setTextColor(Color.RED);
        }
        else {
            _accountBalanceField.setTextColor((Color.BLACK));
        }
    }

    /**
     * Generate a pop-up menu based on R.menu.accountsmenu on click of the menu button.
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
     * @param item is the menu item.
     * @return true when menu item is clicked.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

//            case R.id.addtransaction:
//                addTransaction();
//                return true;
            case R.id.refresh:
                updateData();
                return true;
            case R.id.logOutMenuItem:
                ParseUser.logOut();
                startActivity(new Intent(this, LoginActivity.class));
                return true;
//            case R.id.recordAccountItem:
//                Intent record = new Intent(this, AccountRecordActivity.class);
//                record.putExtra("accountID", _accountIdentifier);
//                startActivity(record);
//                return true;
//            case R.id.deficitChartItem:
//                AccountRecord ar = new AccountRecord(new Date(0), new Date(), _account);
//
//                if (ar.getGraphRecords().size() >= 5) {
//                Intent chart = new Intent(this, TrendsActivity.class);
//                chart.putExtra("accountID", _accountIdentifier);
//                startActivity(chart);
//                return true;
//                }
//                Toast.makeText(getApplicationContext(), "Sub-account must have at least 5 transactions to utilize this function. ", Toast.LENGTH_SHORT).show();
//                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Updates and refreshes display of list of transactions with information from the (parse) database.
     */
    public void updateData() {
        ParseQuery<Transaction> query = ParseQuery.getQuery(Transaction.class);
        query.whereEqualTo("userAccount", _account);
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
                    _account.setAccountValue(_account.getInitialValue());
                    for (int i = 0; i < txns.size(); i++) {
                        _adapter.add(txns.get(i));
                        _account.setAccountValue(_account.getAccountValue() + txns.get(i).getTransactionValue());
                    }

                    _numTransactionsField.setText("" + _adapter.getCount());
                    _accountBalanceField.setText(String.format("$%.2f", _account.getAccountValue()));
                }
                else {
                    Log.i(this.getClass().getName(), "No transactions!");
                }
            }
        });

       if (_account.getAccountValue() < 0.0) {
           _accountBalanceField.setTextColor(Color.RED);
       }
       else {
           _accountBalanceField.setTextColor((Color.BLACK));
       }

    }

    /**
     * Update (parse) database upon button click.
     *
     * @param adapterView View of the account adapter
     * @param view View used to get context of current activity
     * @param i first int
     * @param l first long
     */
    // TODO Implement the onItemClickListener
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // This should just gray out all of the EditTexts and only update if data was changed
        // Maybe I should implement a UserAccount.equals method.
        Intent intent = new Intent(this, TransactionActivity.class);
        intent.putExtra(accID, getIntent().getIntExtra("accountID", 0));
        intent.putExtra(accName, _accountNameField.getText().toString());
        intent.putExtra(edit, true);

        Transaction toSend = _adapter.getTransAtPos(i);
        intent.putExtra("txnName", toSend.getTransactionName());
        intent.putExtra("txnVal", toSend.getTransactionValue()+"");
        intent.putExtra("txnDate", toSend.getTransactionDate().toString());
        intent.putExtra("txnDes", toSend.getDescription());






        //Log.i(getClass().getName(), "Now getting records!");

        startActivityForResult(intent, 1);
    }

    /**
     * Add new transaction to database.
     */
    private void addTransaction() {
        Intent intent = new Intent(this, TransactionActivity.class);
        intent.putExtra(accID, getIntent().getIntExtra("accountID", 0));
        intent.putExtra(accName, _accountNameField.getText().toString());
        intent.putExtra(edit, true);
        startActivityForResult(intent, 1);
    }
}
