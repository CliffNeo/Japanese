package com.example.practice;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

public class CheckableLinearLayout extends LinearLayout implements Checkable {
	private boolean mChecked;
	@SuppressWarnings("unused")
	private Context context;
	
	public CheckableLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void setChecked(boolean checked) {
		// TODO 自动生成的方法存根
		mChecked = checked;
		setBackgroundColor(checked ? Color.parseColor("#7FFFD4") : Color.WHITE);
	}

	@Override
	public boolean isChecked() {
		// TODO 自动生成的方法存根
		return mChecked;
	}

	@Override
	public void toggle() {
		// TODO 自动生成的方法存根
		setChecked(!mChecked);
	}

}
