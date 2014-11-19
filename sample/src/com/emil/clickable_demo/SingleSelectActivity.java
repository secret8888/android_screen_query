package com.emil.clickable_demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.emil.clickable.listener.OnSpanbableClickListener;
import com.emil.clickable.single.SingleClickableClient;
import com.emil.clickable_demo.annotation.Injector;
import com.emil.clickable_demo.annotation.ViewId;

public class SingleSelectActivity extends Activity implements
		OnSpanbableClickListener {

	@ViewId(R.id.tv_title)
	private TextView titleView = null;

	@ViewId(R.id.tv_tip)
	private TextView tipView = null;
	
	@ViewId(R.id.tv_result)
	private TextView resultView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_clickable);
		Injector.inject(this, this);
		SingleClickableClient.getInstance().setTipSpan(this, titleView,
				getString(R.string.title_sample), Color.BLUE, Color.WHITE,
				Color.WHITE, Color.BLACK);
		
		SingleClickableClient.getInstance().setTipSpan(this, tipView,
				getString(R.string.tip_sample), Color.BLUE, Color.WHITE,
				Color.WHITE, Color.BLACK);
	}

	@Override
	public void onSpanbableClick(int textIndex, int spanTextPosition, String spanText) {
		Log.d("TAG", "textIndex : " + textIndex);
		Log.d("TAG", "spanText : " + spanText);
		resultView.setText("textIndex : " + textIndex + "\n" + "spanText : " + spanText);
	}
}
