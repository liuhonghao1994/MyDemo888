package com.atguigu.l10_myapp;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class RollActivity extends Activity {
	private MediaPlayer player;
	private Button button1;
	private Button button2;
	private EditText editText1;
	private EditText editText2;
	private View.OnLongClickListener onLongClickListener=new View.OnLongClickListener() {
		String returninfo =null;
			@Override
			public boolean onLongClick(View v) {
				if(v==button1){
				String key=editText1.getText().toString();
				SharedPreferences sp = getSharedPreferences("qq", MODE_PRIVATE);
				String string = sp.getString(key, null);
				editText2.setText(string );
				
				}
				return true;
			}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_roll);
		editText1=(EditText) findViewById(R.id.editText1);
		editText2=(EditText) findViewById(R.id.editText2);
		button1=(Button) findViewById(R.id.button1);
		button1.setOnLongClickListener(onLongClickListener);
	}
	public void stick(View v)
	{	
		 	Intent intent = new Intent(this,MyService.class);
			intent.putExtra("action", "pause");
			startService(intent);
			String key=editText1.getText().toString();
			String values=editText2.getText().toString();
		if(TextUtils.isEmpty(key)){
			Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
			editText1.startAnimation(animation);
			startActivity(new Intent(this,RollActivity.class));
			Toast.makeText(RollActivity.this, "您的账号不能为空哦", 0).show();
		}
		if(TextUtils.isEmpty(values)){
			Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
			editText2.startAnimation(animation);
			startActivity(new Intent(this,RollActivity.class));
			Toast.makeText(RollActivity.this, "您密码不能为空哦", 0).show();
		}
		SharedPreferences sp = getSharedPreferences("qq", MODE_PRIVATE);
		String string = sp.getString(key, null);
		if(values.equals(string))
		{
			startActivity(new Intent(this,MyContentActivity.class));
		}else{
			startActivity(new Intent(this,RollActivity.class));
			Toast.makeText(RollActivity.this, "您的账号和密码不匹配", 0).show();
		}
	}
	
	//启动注册按钮
	public void reset(View v)
	{
			Intent intent = new Intent(this,MyService.class);
			intent.putExtra("action", "play");
			startService(intent);
			startActivity(new Intent(this,Myset.class));
	}
	
}
