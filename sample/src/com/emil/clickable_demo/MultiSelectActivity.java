package com.emil.clickable_demo;

import com.emil.clickable.listener.OnSpanbableClickListener;
import com.emil.clickable.multi.*;
import com.emil.clickable.utils.ScreenUtils;
import com.emil.clickable_demo.annotation.Injector;
import com.emil.clickable_demo.annotation.ViewId;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MultiSelectActivity extends Activity {

	@ViewId(R.id.tv_tip)
	private TextView tipView = null;

	@ViewId(R.id.tv_result)
	private TextView resultView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_clickable);
		Injector.inject(this, this);

		MultiClickableClient.setTipSpan(tipListener, tipView,
				getString(R.string.tip_sample), 0, 0,
				Color.BLACK, Color.WHITE, Color.WHITE);
	}
	
	private void updateTipParaphrase(String spanText, int spanIndex, int spanTextPosition) {
		String[] spanSplits = spanText.split(" ");
        int evaluationLength = ScreenUtils.getInnerLength(spanSplits, spanIndex);
        int selectStart = spanTextPosition + evaluationLength;
        int selectEnd = selectStart + spanSplits[spanIndex].length();
        MultiClickableClient.setTipSpan(tipListener, tipView,
				getString(R.string.tip_sample), selectStart, selectEnd,
				Color.BLACK, Color.WHITE, Color.WHITE);
    }
	
	private OnSpanbableClickListener tipListener = new OnSpanbableClickListener(){

		@Override
		public void onSpanbableClick(int textIndex, int spanTextPosition,
				String spanText) {
			Log.d("TAG", "textIndex : " + textIndex);
			Log.d("TAG", "spanText : " + spanText);
			resultView.setText("textIndex : " + textIndex + "\nspanTextPosition : " + spanTextPosition + "\nspanText : "
					+ spanText);
			/*
			 * 可以查询单词
			 * 获得需要的数据以后可以根据自己的要求，选中一定长度的字符
			 */
			updateTipParaphrase(spanText, textIndex, spanTextPosition);
		}
		
	};
}
