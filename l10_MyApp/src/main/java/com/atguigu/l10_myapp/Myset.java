package com.atguigu.l10_myapp;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Myset extends Activity {
	private EditText et_1;
	private EditText et_2;
	private Button bt_lhh;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myset);
		et_1=(EditText) findViewById(R.id.et_1);
		et_2=(EditText) findViewById(R.id.et_2);
	}
	public void set(View v)
	{
		String key=et_1.getText().toString();
		String values=et_2.getText().toString();
		if(TextUtils.isEmpty(key)){
			//提示用户没有内容
			//加载 动画
			Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
			//启动动画
			et_1.startAnimation(animation);
			
			Toast.makeText(this, "您注册账号不能为空哦", 0).show();
		}
		else if(TextUtils.isEmpty(values)){
			//提示用户没有内容
			//加载 动画
			Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
			//启动动画
			et_2.startAnimation(animation);
		
			Toast.makeText(this, "您注册密码不能为空哦", 0).show();
		}
		else{
		SharedPreferences sp = getSharedPreferences("qq", MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putString(key, values);
		edit.commit();
		startActivity(new Intent(Myset.this, RollActivity.class));
		Toast.makeText(Myset.this,"欢迎成为硅谷一员", 0).show();
		}
	}
}
