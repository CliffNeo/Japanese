package com.example.practice;

import java.util.List;

import com.example.japanese.JDBOH;
import com.example.japanese.Let;
import com.example.japanese.R;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PracticeSelectAdapter extends ArrayAdapter<Let> {
	
	private int resourceId;
	private JDBOH dbHelper;
	private SQLiteDatabase db;
	//private Context mContext;
	
	public PracticeSelectAdapter(Context context,int textViewResourceId,List<Let> objects) {
		super(context, textViewResourceId, objects);
		this.resourceId = textViewResourceId;
		//this.mContext = context;
		dbHelper = new JDBOH(context, "Letter.db", null, 1);
		db = dbHelper.getReadableDatabase();
	}
	
	@Override
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
			holder.display = (TextView) view.findViewById(R.id.select_item_display);
			holder.count = (TextView) view.findViewById(R.id.select_item_count);
			view.setTag(holder);
		}else{
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		holder.display.setText(let.getSpe());
		holder.count.setText("错误数：" + count);
		return view;
	}
	
	class ViewHolder{
		TextView display;
		TextView count;
	}

	@Override
	public long getItemId(int position) {
		return position+100;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}
}
