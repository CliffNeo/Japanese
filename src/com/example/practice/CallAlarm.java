package com.example.practice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CallAlarm extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO �Զ����ɵķ������
		Toast.makeText(context, "��ϰʱ�䵽��������", Toast.LENGTH_SHORT).show();
		Log.d("Alarm", "Alarmed");
		Intent i = new Intent(context,NotificationService.class);
		i.putExtra("name", "aaaa");
		context.startService(i);
	}

}
