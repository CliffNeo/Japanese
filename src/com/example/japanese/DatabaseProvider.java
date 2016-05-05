package com.example.japanese;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {
	
	public static final int PING_DIR = 0;
	public static final int PING_ITEM = 1;
	private static UriMatcher matcher;
	private JDBOH helper;
	static {
		matcher = new UriMatcher(UriMatcher.NO_MATCH);
		matcher.addURI("com.example.japanese.provider", "ping", PING_DIR);
		matcher.addURI("com.example.japanese.provider", "ping/#", PING_ITEM);
	}
	@Override
	public boolean onCreate() {
		helper = new JDBOH(getContext(),"Letter.db", null, 1);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = null;
		switch(matcher.match(uri)){
		case PING_DIR:
			cursor = db.query("ping", projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case PING_ITEM:
			String pro = uri.getPathSegments().get(1);
			cursor = db.query("ping", projection, "pro = ?", new String[]{ pro }, null, null, sortOrder);
			break;
		}
		
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		switch(matcher.match(uri)){
		case PING_DIR:
			return "vnd.android.cursor.dir/vnd.com.example.japanese.provider.ping";
		case PING_ITEM:
			return "vnd.android.cursor.item/vnd.com.example.japanese.provider.ping";
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

}
