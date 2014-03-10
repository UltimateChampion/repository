package com.example.app;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by michaelfalk on 3/9/14.
 */
public class AccountRecord {
    private Date start, end;
    private double balance;
    private List<Transaction> transactions;

    public AccountRecord(Date startDate, Date endDate) {
        start = startDate;
        end = endDate;
        this.balance = 0;

    }

    public String buildRecord(){
        String out = "";

        for (Transaction t: getRecords()) {

            out += "\n\n"+ t.getTransactionName() + "-\n"+ t.getTransactionDate().toString();
        }

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
