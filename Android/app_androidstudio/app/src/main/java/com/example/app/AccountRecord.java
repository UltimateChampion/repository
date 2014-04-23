package com.example.app;


import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;


import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.apache.commons.math3.stat.regression.SimpleRegression;


/**
 * Created by michaelfalk on 3/9/14.
 */

/**
 * A class to build an account record from selected start and end dates.
 */
public class AccountRecord {
    /**
     * Private variables.
     */
    private Date start, end;
    private List<String> accounts;
    private List<Double> accountBalances; //Don't HIT ME FOR THIS. Had to make parallel lists b/c time.
    private String date = "date";
    private double percentDeficit = 0;
    private double percentSurplus = 0;
    private UserAccount u;


    /**
     * Constructor to build an account record bounded by start and end dates.
     *
     * @param startDate beginning date from where to start reading transactions
     * @param endDate date to stop reading transactions
     */
    public AccountRecord(Date startDate, Date endDate, UserAccount u) {

        //System.out.println("weee"+ ((startDate == null) ? "NULL" : "NOT NULL"));
        start = new Date(startDate.getTime());
        //System.out.println("hit");
        end = new Date(endDate.getTime());
        accounts = new ArrayList<String>();
        accountBalances = new ArrayList<Double>();

        String s = ((u == null) ? "NULL" : "NOT NULL");
        Log.e(getClass().getName(), s+" "+u.getAccountName());

        this.u = u;
    }

    /**
     * Creates formatted financial report.
     * @return String representation of the financial report
     */
    public String buildRecord() {
        String out = "Account Report: \n";
        double totalBalance = 0;

        DecimalFormat toMoney = new DecimalFormat("$#,##0.00;-$#,##0.00");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        out += "\nTransactions:\n";
        double plus = 0;
        double minus = 0;
        for (Transaction t: getRecords()) {

            if (t.getTransactionValue() >=0) {
                plus++;
            }
            else {
                minus++;
            }

            if ((t.getTransactionDate().compareTo(start) >= 0) && (t.getTransactionDate().compareTo(end) <= 0)) {
            out += "\tAccount: " + t.getTransactionAccount().getAccountName() + ":\n\tName: " + t.getTransactionName() + "\n\tDate: " + sdf.format(t.getTransactionDate()) + "\n\tAmount Spent: " + toMoney.format(t.getTransactionValue()) + "\n\n";
               totalBalance += t.getTransactionValue();

                if (!accounts.contains(t.getTransactionAccount().toString())) {
                    accounts.add(t.getTransactionAccount().toString());
                    accountBalances.add(new Double(0)); //Initialize subaccount balance
                }
                int index = accounts.indexOf(t.getTransactionAccount().toString());
                accountBalances.set(index, accountBalances.get(index) + t.getTransactionValue());
            }
        }

        percentDeficit = minus/(minus+plus);
        percentSurplus = plus/(minus+plus);

        out += "Account Balances:\n";
        for (int j = 0; j < accounts.size(); j++){
            out += "\t" + accounts.get(j) + ": " + toMoney.format(accountBalances.get(j)) + "\n";
        }

        out += "\n\tTotal Balance: " + toMoney.format(totalBalance) + "\n";

        return out;
    }

    public String buildSubRecord() {
        String out = "Account Report: \n";
        double totalBalance = 0;


        DecimalFormat toMoney = new DecimalFormat("$#,##0.00;-$#,##0.00");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        out += "\nTransactions:\n";
        double plus = 0;
        double minus = 0;
        for (Transaction t: getGraphRecords()) {

            if (t.getTransactionValue() >=0) {
                plus++;
            }
            else {
                minus++;
            }

            if ((t.getTransactionDate().compareTo(start) >= 0) && (t.getTransactionDate().compareTo(end) <= 0)) {
                out += "\tAccount: " + t.getTransactionAccount().getAccountName() + ":\n\tName: " + t.getTransactionName() + "\n\tDate: " + sdf.format(t.getTransactionDate()) + "\n\tAmount Spent: " + toMoney.format(t.getTransactionValue()) + "\n\n";
                totalBalance += t.getTransactionValue();

                if (!accounts.contains(t.getTransactionAccount().toString())) {
                    accounts.add(t.getTransactionAccount().toString());
                    accountBalances.add(new Double(0)); //Initialize subaccount balance
                }
                int index = accounts.indexOf(t.getTransactionAccount().toString());
                accountBalances.set(index, accountBalances.get(index) + t.getTransactionValue());
            }
        }

        percentDeficit = minus/(minus+plus);
        percentSurplus = plus/(minus+plus);

        out += "Account Balances:\n";
        for (int j = 0; j < accounts.size(); j++){
            out += "\t" + accounts.get(j) + ": " + toMoney.format(accountBalances.get(j)) + "\n";
        }

        out += "\n\tTotal Balance: " + toMoney.format(totalBalance) + "\n";

        return out;
    }

    /**
     * Finds records given date boundary information.
     * @return List of transactions found between the start and end date
     */

    public List<Transaction> getRecords() {
        Log.i(getClass().getName(), "Now getting records!");
        // Mapping of Account names to Transaction lists to be returned.
        List<Transaction> list = new LinkedList<Transaction>();

        ParseQuery<Transaction> query = new ParseQuery(Transaction.class);
        // Constraint: all dates from start to end, inclusive
        query.whereEqualTo(date, start);
        query.whereGreaterThan(date, start);
        query.whereEqualTo(date, end);
        query.whereLessThan(date, end);
        // Make sure we only select our current user's transactions
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        //query.whereEqualTo("userAccount", u);

        //include accounts into consideration
        query.include("userAccount");
        query.include("Account");
        // Execute the search in the foreground (we don't want background)
        try {
            list = query.find();
            Log.i(getClass().getName(), "" + list.size());
        } catch (ParseException e) {
            Log.e(getClass().getName(), "Parse exception: " + e.getCode());
        }

        return list;
    }

    public List<Transaction> getGraphRecords() {
        Log.i(getClass().getName(), "Now getting records!");
        // Mapping of Account names to Transaction lists to be returned.
        List<Transaction> list = new LinkedList<Transaction>();

        ParseQuery<Transaction> query = new ParseQuery(Transaction.class);
        // Constraint: all dates from start to end, inclusive
        query.whereEqualTo(date, start);
        query.whereGreaterThan(date, start);
        query.whereEqualTo(date, end);
        query.whereLessThan(date, end);
        // Make sure we only select our current user's transactions
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.whereEqualTo("userAccount", u);

        //include accounts into consideration
        query.include("userAccount");
        query.include("Account");
        // Execute the search in the foreground (we don't want background)
        try {
            list = query.find();
            Log.i(getClass().getName(), "" + list.size());
        } catch (ParseException e) {
            Log.e(getClass().getName(), "Parse exception: " + e.getCode());
        }

        return list;
    }



    /**
     * Compares date objects in AccountRecord object to determine if the end date comes after the start date.
     *
     * @return Boolean indicating that start comes before end.
     */
    public boolean validDateRange() {
        return (!(end.compareTo(start) <= 0));
    }


    public String buildTrendRecord() {

        String out = "";
        List<Transaction> transList = getGraphRecords();
        Collections.sort(transList, new Comparator<Transaction>() {

            public int compare(Transaction a, Transaction b) {

                return (int) (a.getTransactionDate().getTime() - b.getTransactionDate().getTime());
            }
        });

        double cumulativeSurplus = 0;
        double cumulativeDeficit = 0;
        double sum = 0;
        int index = 1;

        SimpleRegression spr = new SimpleRegression();

        for (Transaction t : transList) {

            spr.addData(index, t.getTransactionValue());
            sum += t.getTransactionValue();
            cumulativeSurplus += (t.getTransactionValue() > 0) ? t.getTransactionValue() : 0;
            cumulativeDeficit += (t.getTransactionValue() < 0) ? t.getTransactionValue() : 0;
            index++;

        }
        double avgTransactionCost = sum/index;
        DecimalFormat toMoney = new DecimalFormat("$#,##0.00;-$#,##0.00");
        double regSlope = spr.getSlope();
        double regIntercept = spr.getIntercept();
        double nextValue = regSlope*(index+1)+regIntercept;

        double rVal = Math.abs(spr.getR());
        rVal  = ((double) ((int) (rVal*100)))/100.;



        out += "Trends Report for "+u.getAccountName()+": \n\n" + "Cumulative Surplus: " + toMoney.format(cumulativeSurplus)+
                "\n\nCumulative Deficit: "+ toMoney.format(cumulativeDeficit)+"\n\nAverage Transaction Cost:"+ avgTransactionCost+
                "\n\nTransaction Consistency, Scaled from 0 to 1 (0 being least consistent): "+rVal+"\n\nNext Expected Transaction Value: "+toMoney.format(nextValue);

        return out;
    }
}
