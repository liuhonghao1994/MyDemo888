package com.atguigu.l10_app_animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Guide01Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide01);
	}
	public void start1(View v) {
		startActivity(new Intent(Guide01Activity.this,Guide02Activity.class));
		overridePendingTransition(R.anim.animation_right_in, R.anim.animation_left_out);
		finish();
	}
}
