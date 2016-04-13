package com.example.practice;

import java.util.List;

import com.example.japanese.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PracticeListAdapter extends ArrayAdapter<Practice> {

	private int resourceId;
	
	public PracticeListAdapter(Context context,
			int textViewResourceId, List<Practice> objects) {
		super(context, textViewResourceId, objects);
		// TODO 自动生成的构造函数存根
		this.resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		Practice p = getItem(position);
		View view;
		ViewHolder holder = new ViewHolder();
		if(convertView == null){
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			holder.name = (TextView) view.findViewById(R.id.practice_item_tv_name);
			holder.amount = (TextView) view.findViewById(R.id.practice_item_tv_amount);
			holder.date = (TextView) view.findViewById(R.id.practice_item_tv_date);
			view.setTag(holder);
		}else{
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		holder.name.setText(p.getName());
		holder.amount.setText("共" + p.getAmount() + "个平假名");
		holder.date.setText("完成后" + p.getTime() + "天再次复习");
		return view;
	}
	
	class ViewHolder{
		TextView name;
		TextView amount;
		TextView date;
	}
}
