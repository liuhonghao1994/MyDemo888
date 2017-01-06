package com.atguigu.l10_app_animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Guide03Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide03);
	}
	public void finish3(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(Guide03Activity.this,MainActivity.class));
		overridePendingTransition(R.anim.animation_bottom_in, R.anim.animation_top_out);
		finish();
	}
	public void back3(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(Guide03Activity.this,Guide02Activity.class));
		overridePendingTransition(R.anim.animation_left_in, R.anim.animation_right_out);
		finish();
	}
	
}
