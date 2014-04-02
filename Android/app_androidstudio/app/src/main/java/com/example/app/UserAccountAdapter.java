package com.example.app;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAccountAdapter extends ArrayAdapter<UserAccount> {
	private Context context;
	private List<UserAccount> uAccounts;
    
    /**
     * Constructs a UserAccountAdapter with a handle to the system and its objects
     * @param context a handle to the system
     * @param objects the list of objects
     */
	public UserAccountAdapter(Context context, List<UserAccount> objects) {
		super(context, R.layout.accounts_row_item, objects);
		this.context = context;
		this.uAccounts = objects;
	}
    
    /**
     * Gets a View that displays the user account at the specified position in the data set
     * @param position the position of the user account within the adapter's data set
     * @param convertView the old view to reuse
     * @param parent the parent that this view will be attached to
     * @return View the view corresponding to the data at the specified position
     */
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater lf = LayoutInflater.from(context);
			convertView = lf.inflate(R.layout.accounts_row_item, null);
		}
		
		UserAccount uac = uAccounts.get(position);
		
		TextView name = (TextView) convertView.findViewById(R.id.accountName);
        TextView value = (TextView) convertView.findViewById(R.id.account_value);
		name.setText(uac.getAccountName());
		value.setText("$" + String.format("%.2f", uac.getAccountValue()));
        if(uac.getAccountValue() < 0.0) {
            value.setTextColor(Color.RED);
        }
        else {
            value.setTextColor(Color.BLACK);
        }
		
		return convertView;
	}
    
    public List<UserAccount> getList() {
        return uAccounts;
    }
}
