package com.example.m3;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBController {
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_USERNAME = "user_name";
	public static final String KEY_PASSWORD = "pass_word";
	public static final String KEY_REALNAME = "real_name";
	
	public static final String DB_NAME = "m3";
	public static final String DB_TABLE = "accounts";
	public static final int DB_VERSION = 2;
	
	private DBHelper M3Helper;
	private Context ourContext;
	private SQLiteDatabase ourDB;
	
	public DBController(Context c) {
		
		ourContext = c;
	}
	
	public DBController open() {
		
		M3Helper = new DBHelper(ourContext);
		ourDB = M3Helper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		
		M3Helper.close();
	}
	
	// adds account to DB
	public long createAccount(String username, String password, String realName) {
		
		ContentValues cv = new ContentValues();
		cv.put(KEY_USERNAME, username);
		cv.put(KEY_PASSWORD, password);
		cv.put(KEY_REALNAME, realName);
		
		return ourDB.insert(DB_TABLE, null, cv);
	}
	
	public String getData() {
		
		String[] columns = new String[] {KEY_ROWID, KEY_USERNAME, KEY_PASSWORD, KEY_REALNAME};
		Cursor c = ourDB.query(DB_TABLE, columns, null, null, null, null, null);
		
		String out = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iUsername = c.getColumnIndex(KEY_USERNAME);
		int iPassword = c.getColumnIndex(KEY_PASSWORD);
		int iActualName = c.getColumnIndex(KEY_REALNAME);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			
			out += c.getString(iRow) + " " + c.getString(iUsername) + " " + c.getString(iPassword) + " " + c.getString(iActualName)+ "\n";
		//	out +=  c.getString(iActualName)+ "\n";
		
		}
		
		return out;
	}
	
	public String getPassword(String username) {
		
		String[] columns = new String[] {KEY_ROWID, KEY_USERNAME, KEY_PASSWORD, KEY_REALNAME};
		Cursor c = ourDB.query(DB_TABLE, columns, null, null, null, null, null);
		
		String out = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iUsername = c.getColumnIndex(KEY_USERNAME);
		int iPassword = c.getColumnIndex(KEY_PASSWORD);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			
			if (c.getString(iUsername).equals(username)) {
				
				return c.getString(iPassword);
			}
		}
		
		return null;
	}
	
	public String getActualName(String username) {
		
		String[] columns = new String[] {KEY_ROWID, KEY_USERNAME, KEY_PASSWORD, KEY_REALNAME};
		Cursor c = ourDB.query(DB_TABLE, columns, null, null, null, null, null);
		
		String out = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iUsername = c.getColumnIndex(KEY_USERNAME);
		int iPassword = c.getColumnIndex(KEY_PASSWORD);
		int iActualName = c.getColumnIndex(KEY_REALNAME);
		
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			
			if (c.getString(iUsername).equals(username)) {
				
				return c.getString(iActualName);
			}
		}
		
		return null;
	}
	

	private class DBHelper extends SQLiteOpenHelper {

		public DBHelper(Context context) {
			
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL("CREATE TABLE "+DB_TABLE+" ("+KEY_ROWID+" INTEGER PRIMARY KEY, "+KEY_USERNAME+" TEXT NOT NULL, "+KEY_PASSWORD+" TEXT NOT NULL, "+ KEY_REALNAME+" TEXT NOT NULL);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
			db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
			onCreate(db);
		}
		
	}	
}
