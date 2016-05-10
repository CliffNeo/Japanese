package com.example.japanese;

import com.example.practice.PracticeListActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private JDBOH dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		dbHelper = new JDBOH(this, "Letter.db", null, 1);
		dbHelper.getWritableDatabase();
		Button randomTest = (Button) findViewById(R.id.button_start_randomtest);
		randomTest.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				TestActivity.actionStart(MainActivity.this,new ping().getPings());
				TestActivity.actionStart(MainActivity.this);
			}
			
		});
		Button list = (Button) findViewById(R.id.button_start_list);
		list.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				ListActivity.actionStart(MainActivity.this);
			}
			
		});
		Button practice = (Button) findViewById(R.id.button_start_practice);
		practice.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				PracticeListActivity.actionStart(MainActivity.this);
			}
			
		});
	}

}
