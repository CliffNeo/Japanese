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
		// TODO �Զ����ɵĹ��캯�����
	}

	@Override
	public void setChecked(boolean checked) {
		// TODO �Զ����ɵķ������
		mChecked = checked;
		setBackgroundColor(checked ? Color.parseColor("#7FFFD4") : Color.WHITE);
	}

	@Override
	public boolean isChecked() {
		// TODO �Զ����ɵķ������
		return mChecked;
	}

	@Override
	public void toggle() {
		// TODO �Զ����ɵķ������
		setChecked(!mChecked);
	}

}
