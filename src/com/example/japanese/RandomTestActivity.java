package com.example.japanese;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.os.Handler;
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
	private AnimatorSet in;
    private AnimatorSet out;
    private FrameLayout mFlCardFront;
    private FrameLayout mFlCardBack;
    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
			TestActivityUtil.iniIO(Letter,button1,button2,button3,button4,lm.random());
            backToFront();
        }
    };

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test_layout);
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
		dbHelper = new JDBOH(this, "Letter.db", null, 1);
		db = dbHelper.getWritableDatabase();
        in = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.anim_in);
        out = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.anim_out);
        mFlCardFront = (FrameLayout) findViewById(R.id.card_front);
        mFlCardBack = (FrameLayout)findViewById(R.id.card_back);
        setCameraDistance();
	}

	@Override
	protected void onStart() {
		let = lm.random();
		ra = TestActivityUtil.iniIO(Letter,button1,button2,button3,button4,let);
		super.onStart();
	}

	@Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
	
	public static void actionStart(Context context){
		Intent intent = new Intent(context,RandomTestActivity.class);
		context.startActivity(intent);
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
	
	private void click(boolean rw){
		if(rw){
			Toast.makeText(RandomTestActivity.this, "right", Toast.LENGTH_SHORT).show();
            frontToBack();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendMessage(new Message());
                }
            }).start();
		}else{
            TestActivityUtil.click(false,RandomTestActivity.this,db,let);
		}
	}

    private void setCameraDistance() {
        int distance = 16000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mFlCardFront.setCameraDistance(scale);
        mFlCardBack.setCameraDistance(scale);
        backToFront();
    }

    private void frontToBack(){
        out.setTarget(mFlCardFront);
        in.setTarget(mFlCardBack);
        out.start();
        in.start();
    }

    private void backToFront(){
        out.setTarget(mFlCardBack);
        in.setTarget(mFlCardFront);
        out.start();
        in.start();
    }
}
