package com.example.japanese;

import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LetAdapter extends ArrayAdapter<Let> {
	
	private int resourceId;
	private JDBOH dbHelper;
	public SQLiteDatabase db;
	
	public LetAdapter(Context context,int textViewResourceId,
			List<Let> objects) {
		super(context, textViewResourceId, objects);
		this.resourceId = textViewResourceId;
		dbHelper = new JDBOH(context, "Letter.db", null, 1);
		db = dbHelper.getReadableDatabase();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Let let = getItem(position);
		ViewHolder holder;
		View view;
		
		if(convertView == null){
			holder = new ViewHolder();
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			holder.display = (TextView) view.findViewById(R.id.item_display);
			holder.count = (TextView) view.findViewById(R.id.item_count);
			
			holder.count.setText("错误数:" + "...");
			
			view.setTag(holder);
		}else{
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		holder.display.setText(let.getSpe());
		new LoadDatabaseTask(holder).execute(let);
		return view;
	}
	
	/*@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Let let = getItem(position);
		Cursor result = db.query("ping",new String[]{ "wrco"}, "pro=?", new String[]{let.getSpe()}, null, null, null);
		int count = 0;
		if(result.moveToFirst()){
			do{
				count = result.getInt(result.getColumnIndex("wrco"));
			}while(result.moveToNext());
		}
		View view;
		ViewHolder holder = new ViewHolder();
		if(convertView == null){
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			holder.display = (TextView) view.findViewById(R.id.item_display);
			holder.count = (TextView) view.findViewById(R.id.item_count);
			view.setTag(holder);
		}else{
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		holder.display.setText(let.getSpe());
		holder.count.setText("��������" + count);
		return view;
	}*///����
	
	class ViewHolder{
		TextView display;
		TextView count;
		boolean done = false;
	}
	
	class LoadDatabaseTask extends AsyncTask<Let, Void, Integer>{

		private ViewHolder vh;
		
		public LoadDatabaseTask(ViewHolder vh){
			this.vh = vh;
		}
		
		@Override
		protected Integer doInBackground(Let... params) {
			Cursor result = db.query("ping",new String[]{ "wrco"}, "pro=?", new String[]{params[0].getSpe()}, null, null, null);
			int count = 0;
			if(result.moveToFirst()){
				do{
					count = result.getInt(result.getColumnIndex("wrco"));
				}while(result.moveToNext());
			}
			return count;
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			vh.count.setText("错误数:" + result);
			vh.done = true;
		}
	}
	
}
