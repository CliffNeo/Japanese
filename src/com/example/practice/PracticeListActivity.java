package com.example.practice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.example.japanese.Let;
import com.example.japanese.LetManage;
import com.example.japanese.TestActivity;
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
import com.example.japanese.R;


public class PracticeListActivity extends OrmLiteBaseActivity<OOH> {
	
	private RuntimeExceptionDao<Practice,String> dao;
	private List<Practice> list;
	private ListView listView;
	private Button add;
	private Button cancle;
	private PracticeListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.practice_layout);
		try {
			dao = getHelper().getUserDataDao();
		} catch (SQLException e) {
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
				Practice p = list.get(position);
                String name = p.getName();
                Practice pra = null;
                List<Let> list = new ArrayList<Let>();

				try {
					dao = getHelper().getUserDataDao();
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
                LetManage lm = new LetManage();
                lm.setList(list);

                TestActivity.actionStart(PracticeListActivity.this,lm);
				//PracticeTestActivity.actionStart(PracticeListActivity.this, p.getName());
			}
			
		});
		add = (Button) findViewById(R.id.practice_button_confirm);
		add.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PracticeListActivity.this,SetPracticeActivity.class);
				startActivityForResult(intent, 1);
			}
			
		});
		cancle = (Button) findViewById(R.id.practice_button_cancle);
		cancle.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
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
