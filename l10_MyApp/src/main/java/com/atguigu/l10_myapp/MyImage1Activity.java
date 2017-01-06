package com.atguigu.l10_myapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MyImage1Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_image1);
	}
	public void start1(View v) {
		startActivity(new Intent(MyImage1Activity.this,MyImage2Activity.class));
		overridePendingTransition(R.anim.animation_right_in, R.anim.animation_left_out);
		finish();
	}
}
