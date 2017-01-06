package com.atguigu.l10_app_animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Guide02Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide02);
	}
	public void start2(View v) {
		startActivity(new Intent(Guide02Activity.this,Guide03Activity.class));
		overridePendingTransition(R.anim.animation_right_in, R.anim.animation_left_out);
		finish();
	}
	public void back2(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(Guide02Activity.this,Guide01Activity.class));
		overridePendingTransition(R.anim.animation_left_in, R.anim.animation_right_out);
		finish();
	}
}
