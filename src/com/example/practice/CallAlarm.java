package com.example.practice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CallAlarm extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO 自动生成的方法存根
		Toast.makeText(context, "复习时间到啦！！！", Toast.LENGTH_SHORT).show();
		Log.d("Alarm", "Alarmed");
		Intent i = new Intent(context,NotificationService.class);
		i.putExtra("name", "aaaa");
		context.startService(i);
	}

}
