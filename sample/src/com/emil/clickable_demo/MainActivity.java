package com.emil.clickable_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onSingleClick(View view) {
		startActivity(new Intent(this, SingleSelectActivity.class));
	}

	public void onMultiClick(View view) {

	}
}
