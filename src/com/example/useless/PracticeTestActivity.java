package com.example.useless;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
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

import com.example.japanese.JDBOH;
import com.example.japanese.Let;
import com.example.japanese.LetManage;
import com.example.japanese.R;
import com.example.japanese.ping;
import com.example.practice.CallAlarm;
import com.example.practice.OOH;
import com.example.practice.Practice;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class PracticeTestActivity extends OrmLiteBaseActivity<OOH> implements OnClickListener{
	
	private RuntimeExceptionDao<Practice,String> dao;
	private JDBOH dbHelper;
	private SQLiteDatabase db = null;
	private Let let;
	private LetManage lm = new LetManage();
	private TextView Letter;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private int ra = 0;
	private String name = "";
	private Practice pra;
	private List<Let> list = new ArrayList<Let>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test_layout);
		try {
			dao = getHelper().getUserDataDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Intent in = getIntent();
		name = in.getStringExtra("name");
		try {
			pra = dao.queryBuilder().where().eq("name", name).query().get(0);
		} catch (SQLException e) {
			Log.d("PracticeTest", "query error");
			e.printStackTrace();
		}
		String[] lets = pra.getPings().split("#");
		for(int i=0;i<lets.length;i++){
			String[] part = lets[i].split("\\-");
			list.add(new Let(part[0],part[1]));
		}
		Letter = (TextView) findViewById(R.id.tv_ping_test_pro);
		button1 = (Button) findViewById(R.id.select_1);
		button1.setOnClickListener(this);
		button2 = (Button) findViewById(R.id.select_2);
		button2.setOnClickListener(this);
		button3 = (Button) findViewById(R.id.select_3);
		button3.setOnClickListener(this);
		button4 = (Button) findViewById(R.id.select_4);
		button4.setOnClickListener(this);
		lm = new ping().getPings();
		iniOI();
	}

	@Override
	public void onClick(View v) {
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

	public void iniOI(){
		Log.d("test", "INIing");
		if(list.size() < 1){
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(System.currentTimeMillis());
			c.add(Calendar.DAY_OF_YEAR, pra.getTime());
			c.set(Calendar.HOUR_OF_DAY, 9);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			Intent intent = new Intent(PracticeTestActivity.this,CallAlarm.class);
			PendingIntent sender = PendingIntent.getBroadcast(PracticeTestActivity.this, 0, intent, 0);
			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
			finish();
		}else{
			Log.d("test", String.valueOf(list.size()));
			let = list.get(0);
			list.remove(0);
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
	
	public static void actionStart(Context context,String name){
		Intent intent = new Intent(context,PracticeTestActivity.class);
		intent.putExtra("name", name);
		context.startActivity(intent);
	}
	
	private void click(boolean rw){
		if(rw){
			Toast.makeText(PracticeTestActivity.this, "right", Toast.LENGTH_SHORT).show();
			iniOI();
		}else{
			Toast.makeText(PracticeTestActivity.this, "wrong", Toast.LENGTH_SHORT).show();
			new Thread(new Runnable(){

				@Override
				public void run() {
					if(db == null){
						dbHelper = new JDBOH(PracticeTestActivity.this, "Letter.db", null, 1);
						db = dbHelper.getWritableDatabase();
					}
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
				
			}).start();
			
		}
	}
}
