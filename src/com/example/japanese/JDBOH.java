package com.example.japanese;

import java.util.Iterator;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class JDBOH extends SQLiteOpenHelper {
	
	@SuppressWarnings("unused")
	private Context mContext;
	
	public static final String CREATE_LETTTER = "create table ping ("
			+ "pro text primary key,"
			+ "_id integer default 0,"
			+ "wrco integer default 0)";
	
	public JDBOH(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_LETTTER);
		iniDB(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	
	private void iniDB(SQLiteDatabase db){
		
		ContentValues values = new ContentValues();
		ping ini = new ping();
		Iterator<Let> it = ini.getPings().lookinto();
		int count = 0;
		while(it.hasNext()){
			Let a = it.next();
			values.put("pro", a.getSpe());
			values.put("_id", count++);
			db.insert("ping", null, values);
		}
	}
}
