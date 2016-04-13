package com.example.practice;

import java.sql.SQLException;
import java.util.List;

import com.example.japanese.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SetPracticeActivity extends OrmLiteBaseActivity<OOH> implements OnClickListener{
	
	private RuntimeExceptionDao<Practice,String> dao;
	private EditText name;
	private Button select;
	private Spinner data;
	private Integer selected_data;
	private Button confirm;
	private Button cancle;
	private TextView hint;
	private LinearLayout layout; 
	private InputMethodManager imm;
	private String ping = null;
	private int amount = 0;
	private String rightname = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_practice_layout);
		
		imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		try {
			dao = getHelper().getUserDataDao();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		name = (EditText) findViewById(R.id.set_practice_edittext_name);
		name.setFocusable(true);
		select = (Button) findViewById(R.id.set_practice_button_selectword);
		select.setOnClickListener(this);
		select.setFocusable(true);
		data = (Spinner) findViewById(R.id.set_practice_spinner);
		final Integer[] data_list = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,data_list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		data.setAdapter(adapter);
		data.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO �Զ����ɵķ������
				selected_data = data_list[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO �Զ����ɵķ������
				selected_data = Integer.valueOf(7);
			}
			
		});
		confirm = (Button) findViewById(R.id.set_practice_button_confirm);
		confirm.setOnClickListener(this);
		confirm.setOnClickListener(this);
		cancle = (Button) findViewById(R.id.set_practice_button_cancle);
		cancle.setOnClickListener(this);
		hint = (TextView) findViewById(R.id.set_practice_textview_hint);
		name.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO �Զ����ɵķ������
				if(hasFocus){
					
				}else{
					imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
					String na = name.getText().toString();
					if(na.equals("")){
						hint.setText("����Ϊ��");
					}else{
						new AsyncTask<String, Integer, Boolean>(){
							
							List<Practice> pra;
							String par;
							
							@Override
							protected Boolean doInBackground(String... params) {
								// TODO �Զ����ɵķ������
								try {
									par = params[0];
									pra = dao.queryBuilder().where().eq("name", params[0]).query();
									Log.d("AsyncTask", "searching");
									return true;
								} catch (SQLException e) {
									// TODO �Զ����ɵ� catch ��
									e.printStackTrace();
									return false;
								}
							}

							@Override
							protected void onPostExecute(Boolean result) {
								// TODO �Զ����ɵķ������
								if(pra.size()>0){
									hint.setText("�����ظ�");
								}else{
									hint.setText("���ƿ���");
									rightname = par;
								}
							}
							
						}.execute(na);
						
					}
				}
			}
			
		});
		layout = (LinearLayout) findViewById(R.id.set_practice_layout_main);
		layout.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO �Զ����ɵķ������
				v.performClick();
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					layout.requestFocus();
					layout.requestFocusFromTouch();
					return true;
				}
				return false;
			}
			
		});;
	}

	@Override
	public void onClick(View v) {
		// TODO �Զ����ɵķ������
		switch(v.getId()){
		case R.id.set_practice_button_selectword:
			select.requestFocus();
			select.requestFocusFromTouch();
			Intent in = new Intent(SetPracticeActivity.this,SelectActivity.class);
			startActivityForResult(in, 1);
			break;
		case R.id.set_practice_button_confirm:
			if(rightname != null && amount >0){
				ProgressDialog pd = new ProgressDialog(SetPracticeActivity.this);//����̫��ûʲô����
				pd.setTitle("������");
				pd.setMessage("���Ժ�");
				pd.setCancelable(false);
				pd.show();
				Practice p = new Practice(rightname,ping,amount,selected_data);
				dao.create(p);

				pd.dismiss();
				Toast.makeText(this, "����ɹ���", Toast.LENGTH_SHORT).show();
				setResult(1);
				Log.d("test", "setResult:1");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				finish();
			}else if(rightname == null && amount <= 0){
				Toast.makeText(this, "������������Ʋ�ѡ��Ҫ��ϰ��ƽ����", Toast.LENGTH_SHORT).show();
			}else if(rightname == null){
				Toast.makeText(this, "�������������", Toast.LENGTH_SHORT).show();
			}else if(amount <= 0){
				Toast.makeText(this, "��ѡ��Ҫ��ϰ��ƽ����", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.set_practice_button_cancle:
			setResult(2);
			finish();
		}
		
	}
	
	public static void actionStart(Context context){
		Intent intent = new Intent(context,SetPracticeActivity.class);
		context.startActivity(intent);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO �Զ����ɵķ������
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 1 && resultCode > 0){
			select.setText("ѡ�񵥴ʣ���ѡ��" + resultCode + ")");
			ping = data.getStringExtra("result");
			amount = data.getIntExtra("amount", 7);
		}else if(requestCode == 1 && resultCode == 0){
			select.setText("ѡ�񵥴�");
		}
	}
}
