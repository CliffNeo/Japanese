package com.example.japanese;

import java.util.*;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RandomTestActivity extends Activity implements OnClickListener{
	
	private LetManage lm = new LetManage();
	private JDBOH dbHelper;
	private SQLiteDatabase db;
	private Let let;
	private TextView Letter;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private int ra = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test_layout);
		Letter = (TextView) findViewById(R.id.display);
		button1 = (Button) findViewById(R.id.select_1);
		button1.setOnClickListener(this);
		button2 = (Button) findViewById(R.id.select_2);
		button2.setOnClickListener(this);
		button3 = (Button) findViewById(R.id.select_3);
		button3.setOnClickListener(this);
		button4 = (Button) findViewById(R.id.select_4);
		button4.setOnClickListener(this);
		lm = new ping().getPings();
		dbHelper = new JDBOH(this, "Letter.db", null, 1);
		db = dbHelper.getWritableDatabase();
		iniOI();
	}
	
	public void iniOI(){
		let = lm.random();
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
			choiceButton(button1, list);
			//button1.setText(let.getPro());
			break;
		case 2:
			list.remove(1);
			choiceButton(button2, list);
			//button2.setText(let.getPro());
			break;
		case 3:
			list.remove(2);
			choiceButton(button3, list);
			//button3.setText(let.getPro());
			break;
		case 4:
			list.remove(3);
			choiceButton(button4, list);
			//button4.setText(let.getPro());
			break;
		}
	}
	
	private void choiceButton(Button bu,List<Button> list){
		String rightPro = let.getPro();
		bu.setText(rightPro);
		for(Button a:list){
			Let ran = lm.random();
			while(rightPro.contains(ran.getPro())){
				ran = lm.random();
			}
			a.setText(ran.getPro());
			rightPro += ran.getPro();
		}
	}
	
	public static void actionStart(Context context){
		Intent intent = new Intent(context,RandomTestActivity.class);
		context.startActivity(intent);
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
	
	private void click(boolean rw){
		if(rw){
			Toast.makeText(RandomTestActivity.this, "right", Toast.LENGTH_SHORT).show();
			iniOI();
		}else{
			Toast.makeText(RandomTestActivity.this, "wrong", Toast.LENGTH_SHORT).show();
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
		}
	}
}
