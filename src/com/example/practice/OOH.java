package com.example.practice;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class OOH extends OrmLiteSqliteOpenHelper {
	
	private static final String dbName = "Practice.db";
	private static final int dbVersion = 1;
	private Dao<Practice,String> userDao = null;
	private RuntimeExceptionDao<Practice,String> userRuntimeDao = null;
	
	public OOH(Context context) {
		super(context, dbName, null, dbVersion);
	}
	
	public OOH(Context context, String databaseName, CursorFactory factory,
			int databaseVersion) {
		super(context, databaseName, factory, databaseVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		// TODO 自动生成的方法存根
		try{
			TableUtils.createTable(arg1, Practice.class);
			userDao = getUserDao();
			userRuntimeDao = getUserDataDao();
		}catch(Exception e){
			Log.d("test", e.toString());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		// TODO 自动生成的方法存根

	}
	
	private Dao<Practice,String> getUserDao() throws SQLException{
		if(userDao == null){
			userDao = getDao(Practice.class);
		}
		return userDao;
	}
	
	public RuntimeExceptionDao<Practice,String> getUserDataDao() throws SQLException{
		if(userRuntimeDao == null){
			userRuntimeDao = getRuntimeExceptionDao(Practice.class);
		}
		return userRuntimeDao;
	}

	@Override
	public void close() {
		// TODO 自动生成的方法存根
		super.close();
		userRuntimeDao = null;
	}
	
}
