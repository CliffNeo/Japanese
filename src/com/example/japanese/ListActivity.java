package com.example.japanese;

import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends Activity {
	
	private List<Let> list = new ping().getPings().getList();
	private int loca = -1;
	private JDBOH dbHelper;
	private SQLiteDatabase db;
	private Let letter;
	private LetAdapter adapter;

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			int count = msg.what;
			ListView lv = (ListView) findViewById(R.id.list);
			LinearLayout a = (LinearLayout) lv.getChildAt(loca);
			TextView b = (TextView) a.findViewById(R.id.item_count);
			b.setText("错误数:" + count);
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.list_layout);
		adapter = new LetAdapter(ListActivity.this, R.layout.list_item_layout, list);
		ListView listView = (ListView) findViewById(R.id.list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Let let = list.get(position);
				letter = let;
				loca = position;
				Intent intent = new Intent(ListActivity.this,ListClickActivity.class);
				intent.putExtra("Spe", let.getSpe());
				intent.putExtra("Pro", let.getPro());
				startActivityForResult(intent, 1);
			}
			
		});
		
	}

	@Override
	protected void onDestroy() {
		adapter.db.close();
		db.close();
		super.onDestroy();
	}

	public static void actionStart(Context context){
		Intent intent = new Intent(context,ListActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		dbHelper = new JDBOH(this, "Letter.db", null, 1);
		db = dbHelper.getReadableDatabase();
		if(requestCode == 1 && resultCode > 0){
			
			new Thread(new Runnable(){

				@Override
				public void run() {
					Cursor result = db.query("ping",new String[]{ "wrco"}, "pro=?", new String[]{letter.getSpe()}, null, null, null);
					int count = 0;
					if(result.moveToFirst()){
						do{
							count = result.getInt(result.getColumnIndex("wrco"));
							Log.d("DB",String.valueOf(count));
						}while(result.moveToNext());
					}else {
						Log.d("DB","empty");
					}
					Message message = new Message();
					message.what = count;
					handler.sendMessage(message);
				}
				
			}).start();
			
		}
	}

}
