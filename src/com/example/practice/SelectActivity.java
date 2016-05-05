package com.example.practice;

import java.util.ArrayList;
import java.util.List;

import com.example.japanese.Let;
import com.example.japanese.R;
import com.example.japanese.ping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class SelectActivity extends Activity {
	
	private List<Let> list = new ping().getPings().getList();
	private List<Let> selectList = new ArrayList<Let>();
	ListView listView = null;
	Button confirm = null;
	Button cancle = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.set_practice_select_layout);
		PracticeSelectAdapter adapter = new PracticeSelectAdapter(SelectActivity.this, R.layout.set_practice_select_item_layout, list);
		listView = (ListView) findViewById(R.id.set_practice_select_list);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Let let = list.get(position);
				if(!selectList.contains(let)){
					selectList.add(let);
				}else{
					if(selectList.size() > 0){
						selectList.remove(position);
					}
				}
				//long[] ids = listView.getCheckedItemIds();
			}
			
		});
		
		confirm = (Button) findViewById(R.id.select_button_confirm);
		confirm.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				String re = "";
				for(Let a : selectList){
					re += a.getSpe() + "-" + a.getPro()+ "#";
				}
				intent.putExtra("result", re);
				intent.putExtra("amount", selectList.size());
				setResult(listView.getCheckedItemCount(),intent);
				finish();
			}
			
		});
		
		cancle = (Button) findViewById(R.id.select_button_cancle);
		cancle.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				setResult(0,null);
				finish();
			}
			
		});
	}
}
