package com.example.practice;

import java.sql.SQLException;
import java.util.List;

import com.example.japanese.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class PracticeListActivity extends OrmLiteBaseActivity<OOH> {
	
	private RuntimeExceptionDao<Practice,String> dao;
	private List<Practice> list;
	private ListView listView;
	private Button add;
	private Button cancle;
	private PracticeListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.practice_layout);
		try {
			dao = getHelper().getUserDataDao();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		list = dao.queryForAll();
		adapter = new PracticeListAdapter(PracticeListActivity.this,R.layout.practice_item_layout,list);
		listView = (ListView) findViewById(R.id.practice_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO �Զ����ɵķ������
				Practice p = list.get(position);
				PracticeTestActivity.actionStart(PracticeListActivity.this, p.getName());
			}
			
		});
		add = (Button) findViewById(R.id.practice_button_confirm);
		add.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				Intent intent = new Intent(PracticeListActivity.this,SetPracticeActivity.class);
				startActivityForResult(intent, 1);
			}
			
		});
		cancle = (Button) findViewById(R.id.practice_button_cancle);
		cancle.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				finish();
			}
			
		});
	}
	
	public static void actionStart(Context context){
		Intent intent = new Intent(context,PracticeListActivity.class);
		context.startActivity(intent);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO �Զ����ɵķ������
		super.onActivityResult(requestCode, resultCode, data);
		Log.d("test", String.valueOf(resultCode)+" and " + String.valueOf(requestCode));
		if(requestCode == 1 && resultCode == 1){
			List<Practice> alist = dao.queryForAll();
			int size = alist.size();
			list.add(alist.get(size-1));
			adapter.notifyDataSetChanged();
		}
	}
	
	
}
