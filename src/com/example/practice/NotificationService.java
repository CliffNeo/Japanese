package com.example.practice;

import com.example.japanese.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class NotificationService extends Service {
	
	NotificationManager nm = null;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		showNotification(intent);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	protected void showNotification(Intent intent){
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
		
		//String name  = intent.getStringExtra("name");
		
		mBuilder.setContentTitle("title");
		mBuilder.setContentText("��ϰʱ�䵽����");
		mBuilder.setSmallIcon(R.drawable.ic_launcher);
		mBuilder.setWhen(System.currentTimeMillis());
		mBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
		mBuilder.setTicker("��ϰʱ�䵽��!");
		Notification no = mBuilder.build();
		no.flags = Notification.FLAG_AUTO_CANCEL;
		
		nm.notify(1, no);
	}
}
