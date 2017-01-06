package com.atguigu.l10_myapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MyImage3Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_image3);
	}
	public void finish3(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(MyImage3Activity.this,RollActivity.class));
		overridePendingTransition(R.anim.animation_bottom_in, R.anim.animation_top_out);
		finish();
		
	}
	public void back3(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(MyImage3Activity.this,MyImage2Activity.class));
		overridePendingTransition(R.anim.animation_left_in, R.anim.animation_right_out);
		finish();
	}
	
}
