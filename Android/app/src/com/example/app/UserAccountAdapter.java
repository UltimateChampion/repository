package com.example.app;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class UserAccountAdapter extends ArrayAdapter<UserAccount> {
	private Context context;
	private List<UserAccount> uAccounts;
	
	public UserAccountAdapter(Context context, List<UserAccount> objects) {
		super(context, R.layout.accounts_row_item, objects);
		this.context = context;
		this.uAccounts = objects;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater lf = LayoutInflater.from(context);
			convertView = lf.inflate(R.layout.accounts_row_item, null);
		}
		
		UserAccount uac = uAccounts.get(position);
		
		TextView name = (TextView) convertView.findViewById(R.id.accountName);
		name.setText(uac.getAccountName());
		
		// TextView numItems = (TextView) convertView.findViewById(R.id.account_items);
		// numItems.setText(uac.getTxnList().size());
		
		return convertView;
	}
}
