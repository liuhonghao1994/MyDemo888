package com.atguigu.l10_myapp;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MyImage2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_image2);
	}public void start2(View v) {
		startActivity(new Intent(MyImage2Activity.this,MyImage3Activity.class));
		overridePendingTransition(R.anim.animation_right_in, R.anim.animation_left_out);
		finish();
	}
	public void back2(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(MyImage2Activity.this,MyImage1Activity.class));
		overridePendingTransition(R.anim.animation_left_in, R.anim.animation_right_out);
		finish();
	}
	
}
