package com.example.useless;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.japanese.JDBOH;
import com.example.japanese.Let;
import com.example.japanese.R;
import com.example.japanese.TestActivityUtil;

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
	private FrameLayout mFlCardBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test_layout);
		intent = getIntent();
		let = new Let(intent.getStringExtra("Spe"), intent.getStringExtra("Pro"));
		Letter = (TextView) findViewById(R.id.tv_ping_test_pro);
		button1 = (Button) findViewById(R.id.select_1);
		button1.setOnClickListener(this);
		button2 = (Button) findViewById(R.id.select_2);
		button2.setOnClickListener(this);
		button3 = (Button) findViewById(R.id.select_3);
		button3.setOnClickListener(this);
		button4 = (Button) findViewById(R.id.select_4);
		button4.setOnClickListener(this);
		mFlCardBack = (FrameLayout)findViewById(R.id.card_back);
		mFlCardBack.setVisibility(View.INVISIBLE);
		dbHelper = new JDBOH(this, "Letter.db", null, 1);
		db = dbHelper.getWritableDatabase();
		ra = TestActivityUtil.iniIO(Letter,button1,button2,button3,button4,let);
	}

	@Override
	protected void onDestroy() {
		db.close();
		super.onDestroy();

	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.select_1:
			TestActivityUtil.click(ra == 1,ListClickActivity.this,db,let);
			break;
		case R.id.select_2:
            TestActivityUtil.click(ra == 2,ListClickActivity.this,db,let);
			break;
		case R.id.select_3:
            TestActivityUtil.click(ra == 3,ListClickActivity.this,db,let);
			break;
		case R.id.select_4:
            TestActivityUtil.click(ra == 4,ListClickActivity.this,db,let);
			break;
		}
	}

}
