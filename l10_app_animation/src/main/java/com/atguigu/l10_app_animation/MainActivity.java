package com.atguigu.l10_app_animation;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText et1;
	private Button bt1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et1=(EditText) findViewById(R.id.et1);
		bt1=(Button) findViewById(R.id.bt1);
	}
	public void mainstart(View v) {
		String name = et1.getText().toString().trim();
		if(TextUtils.isEmpty(name)){
			//提示用户没有内容
			//加载 动画
			Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
			//启动动画
			et1.startAnimation(animation);
		}else{
			//跳转
			startActivity(new Intent(this,ScannerActivity.class));
		}
		 
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
