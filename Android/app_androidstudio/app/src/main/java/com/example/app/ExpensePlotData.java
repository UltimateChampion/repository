package com.example.app;

import android.accounts.Account;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by AmierNaji on 4/19/14.
 */
public class ExpensePlotData {

    public List<Number> getTotalBalance(int pastTransactions, UserAccount uac) {

        Date currentDate = new Date();//get current date
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);


        AccountRecord ar = new AccountRecord(new Date(0), currentDate, uac);
        ArrayList<Transaction> transList = new ArrayList<Transaction>();

        for(Transaction t : ar.getGraphRecords()) {

            transList.add(t);
        }

        Collections.sort(transList,new Comparator<Transaction>() {

            public int compare(Transaction a, Transaction b) {

                return (int) (a.getTransactionDate().getTime()- b.getTransactionDate().getTime());
            }
        });//sort list by date

        ArrayList<Transaction> outList = new ArrayList<Transaction>();
        int listindex = 0;
        for (int i = 0; i< pastTransactions; i++) {

            listindex = transList.size()-i-1;
            if (listindex >= 0) {

            outList.add(transList.get(listindex));
            }
        }

        Collections.sort(outList,new Comparator<Transaction>() {

            public int compare(Transaction a, Transaction b) {

                return (int) (a.getTransactionDate().getTime()- b.getTransactionDate().getTime());
            }
        });//sort this list again

        ArrayList<Number> out = new ArrayList<Number>();
        double currentBalance = 0;
        for (Transaction t : outList) {

            currentBalance += t.getTransactionValue();
            out.add(currentBalance);

        }

        return out;
    }

    public Integer[] getXVals(List<Number> list) {

        int i = 0;
        for (Number n : list) {
            i++;
        }

        Integer[] out = new Integer[i];

        for (int j = 0; j < i; j++) {

            out[j] = j+1;
        }

        return out;
    }
}
