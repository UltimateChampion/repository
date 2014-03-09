package com.example.app;

import java.util.Date;
import java.util.List;

/**
 * Created by michaelfalk on 3/9/14.
 */
public class AccountRecord {
    private Date startDate, endDate;
    private double balance;
    private List<Transaction> transactions;

    public AccountRecord(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.balance = 0;

    }

    public String buildRecord(){
        String out = "";
        transactions = (List) ParseSingleton.getInstance().get("txnList");

        for (Transaction t: transactions){
            Date candidateDate = t.getTransactionDate();
//
//            if (candidateDate.equals(startDate) || (candidateDate.after(startDate) && candidateDate.before(endDate))|| candidateDate.equals(endDate)){
//                out += t.getTransactionAccount().getAccountName().toString()+","+candidateDate.toString()+"\n";
//            }
        }
        return out;
    }
}
