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
		this.resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
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
		holder.amount.setText("五十音数：" + p.getAmount() + "个");
		holder.date.setText("下次复习时间为：" + p.getTime() + "天后");
		return view;
	}
	
	class ViewHolder{
		TextView name;
		TextView amount;
		TextView date;
	}
}
