package com.example.app;

import android.accounts.Account;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by michaelfalk on 3/9/14.
 */
public class AccountRecord {
    private Date start, end;
    private double balance;
    private List<Transaction> transactions;
    private List<String> accounts;
    private List<Double> accountBalances; //Don't HIT ME FOR THIS. Had to make parallel lists b/c time.

    public AccountRecord(Date startDate, Date endDate) {
        start = startDate;
        end = endDate;
        this.balance = 0;
        accounts = new ArrayList<String>();
        accountBalances = new ArrayList<Double>();
    }

    public String buildRecord(){
        String out = "Account Report- \n";
        double totalBalance = 0;

        DecimalFormat dFormat = new DecimalFormat("#.00");

        out+="\nTransactions:";
        for (Transaction t: getRecords()) {

            if ((t.getTransactionDate().compareTo(start) >= 0) && (t.getTransactionDate().compareTo(end) <= 0)) {
            out += "\n\n"+t.getTransactionAccount().toString() +":\n"+ t.getTransactionName() + "-\n"+ t.getTransactionDate().toString() +"\nAmount Spent- $"+dFormat.format(t.getTransactionValue()) ;
            totalBalance += t.getTransactionValue();

                if (!accounts.contains(t.getTransactionAccount().toString())){
                    accounts.add(t.getTransactionAccount().toString());
                    accountBalances.add(new Double(0)); //Initialize subaccount balance
                }
                if (accounts.contains(t.getTransactionAccount())){
                    int index = accounts.indexOf(t.getTransactionAccount().toString());
                    accountBalances.set(accounts.indexOf(index), accountBalances.get(index)+t.getTransactionValue());
                }
            }
        }

        out+="\n\nAccount Balances:";
        for (int j = 0; j < accounts.size(); j++){
            out+="\n\n"+accounts.get(j)+": "+dFormat.format(accountBalances.get(j));
        }

        out += "\n\nTotal Balance- $"+dFormat.format(totalBalance);

        return out;
    }

    private List<Transaction> getRecords() {
        Log.i(getClass().getName(), "Now getting records!");
        // Mapping of Account names to Transaction lists to be returned.
        List<Transaction> list = new LinkedList<Transaction>();

        ParseQuery<Transaction> query = new ParseQuery(Transaction.class);
        // Constraint: all dates from start to end, inclusive
        query.whereEqualTo("date", start);
        query.whereGreaterThan("date", start);
        query.whereEqualTo("date", end);
        query.whereLessThan("date", end);
        // Make sure we only select our current user's transactions
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        // Execute the search in the foreground (we don't want background)
        try {
            list = query.find();
            Log.i(getClass().getName(), "" + list.size());
        } catch (ParseException e) {
            Log.e(getClass().getName(), "Parse exception: " + e.getCode());
        }

        return list;
    }
}
