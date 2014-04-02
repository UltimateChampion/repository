package com.example.app;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by richard on 2/26/14.
 */
public class TransactionAdapter extends ArrayAdapter<Transaction> {
    private Context context;
    private List<Transaction> transactions;
    
    /**
     * Constructs a TransactionAdapter with a handle to the system and its transactions
     * @param context a handle to the system
     * @param transactions the list of transactions
     */
    public TransactionAdapter(Context context, List<Transaction> transactions) {
        super(context, R.layout.transaction_row_item, transactions);
        this.context = context;
        this.transactions = transactions;
    }
    
    /**
     * Gets a View that displays the transaction at the specified position in the data set
     * @param position the position of the transaction within the adapter's data set
     * @param convertView the old view to reuse
     * @param parent the parent that this view will be attached to
     * @return View the view corresponding to the data at the specified position
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater lf = LayoutInflater.from(context);
            convertView = lf.inflate(R.layout.transaction_row_item, null);
        }
        
        Transaction t = transactions.get(position);
        
        TextView name = (TextView) convertView.findViewById(R.id.txnadapter_name);
        TextView value = (TextView) convertView.findViewById(R.id.txnadapter_value);
        TextView date = (TextView) convertView.findViewById(R.id.txnadapter_date);
        
        name.setText(t.getTransactionName());
        value.setText(String.format("$%.2f", t.getTransactionValue()));
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        date.setText(df.format(t.getDate("date")));
        
        if(t.getTransactionValue() < 0.0) value.setTextColor(Color.RED);
        
        return convertView;
    }
}
