package com.example.japanese;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ListClickActivity extends Activity implements OnClickListener{
	
	private JDBOH dbHelper;
	private SQLiteDatabase db;
	private Let let;
	private TextView Letter;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private int ra = 0;
	private Intent intent;
	private LetManage lm = new ping().getPings();
	private int wrco = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test_layout);
		intent = getIntent();
		Log.d("pro",intent.getStringExtra("Spe") );
		let = new Let(intent.getStringExtra("Spe"), intent.getStringExtra("Pro"));
		Letter = (TextView) findViewById(R.id.display);
		button1 = (Button) findViewById(R.id.select_1);
		button1.setOnClickListener(this);
		button2 = (Button) findViewById(R.id.select_2);
		button2.setOnClickListener(this);
		button3 = (Button) findViewById(R.id.select_3);
		button3.setOnClickListener(this);
		button4 = (Button) findViewById(R.id.select_4);
		button4.setOnClickListener(this);
		dbHelper = new JDBOH(this, "Letter.db", null, 1);
		db = dbHelper.getWritableDatabase();
		/*intent = getIntent();
		let = new Let(intent.getStringExtra("spe"), intent.getStringExtra("pro"));*/
		Letter.setText(let.getSpe());
		List<Button> list = new ArrayList<Button>();
		list.add(button1);
		list.add(button2);
		list.add(button3);
		list.add(button4);
		Random r = new Random();
		ra = r.nextInt(4)+1;
		switch(ra){
		case 1:
			list.remove(0);
			choiceButton(button1, list,lm,let);
			//button1.setText(let.getPro());
			break;
		case 2:
			list.remove(1);
			choiceButton(button2, list,lm,let);
			//button2.setText(let.getPro());
			break;
		case 3:
			list.remove(2);
			choiceButton(button3, list,lm,let);
			//button3.setText(let.getPro());
			break;
		case 4:
			list.remove(3);
			choiceButton(button4, list,lm,let);
			//button4.setText(let.getPro());
			break;
		}
	}
	
	public void choiceButton(Button bu,List<Button> list,LetManage lm,Let right){
		String rp = right.getPro();
		//Log.d("pro", rp);
		bu.setText(rp);
		for(Button a:list){
			Let ran = lm.random();
			while(rp.contains(ran.getPro())){
				ran = lm.random();
			}
			a.setText(ran.getPro());
			rp += ran.getPro();
		}
	}
	
	private void click(boolean rw){
		if(rw){
			Toast.makeText(ListClickActivity.this, "right", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent();
			intent.putExtra("wrco", wrco);
			setResult(wrco, intent);
			finish();
		}else{
			Toast.makeText(ListClickActivity.this, "wrong", Toast.LENGTH_SHORT).show();
			new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					synchronized(""){//对下面的区块加锁，保证wrco只能同时被一个线程访问，避免出错
						ContentValues value = new ContentValues();
						Cursor result = db.query("ping",new String[]{ "wrco"}, "pro=?", new String[]{let.getSpe()}, null, null, null);
						int count = 0;
						if(result.moveToFirst()){
							do{
								count = result.getInt(result.getColumnIndex("wrco"));
							}while(result.moveToNext());
						}
						value.put("wrco", ++count);
						db.update("ping", value, "pro=?", new String[]{let.getSpe()});
						result.close();
						wrco++;
					}
				}
				
			}).start();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch(v.getId()){
		case R.id.select_1:
			click(ra == 1);
			break;
		case R.id.select_2:
			click(ra == 2);
			break;
		case R.id.select_3:
			click(ra == 3);
			break;
		case R.id.select_4:
			click(ra == 4);
			break;
		}
	}
	/*public static void actionStart(Context context,Let let){
		Intent intent = new Intent(context,ListClickActivity.class);
		intent.putExtra("spe", let.getSpe());
		intent.putExtra("pro", let.getPro());
		context.startActivity(intent);
	}*/
}
