package com.atguigu.l10_myapp;


import android.R.anim;
import android.R.drawable;
import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SlideActivity extends Activity {
	private RelativeLayout rl_splash;
	private Button bt_splash;
	private int startX;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide);
		rl_splash=(RelativeLayout) findViewById(R.id.rl_splash);
		bt_splash=(Button) findViewById(R.id.bt_splash);
		rl_splash.setOnTouchListener(new OnTouchListener() {
			
			private int moveX;
			private int index=1;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int X=(int) event.getX();
			
				switch(event.getAction())
				{
				case MotionEvent.ACTION_DOWN:
					startX=X;
					
					
					break;
				case MotionEvent.ACTION_MOVE:
					
					
					//显示以偶东后的距离
					 moveX=X-startX;
					
					
					
					
					break;
				case MotionEvent.ACTION_UP:
					if(moveX<-30)
						
					{
						index++;
						
						if(index==1)
						{
							rl_splash.setBackgroundResource(R.drawable.splash_1);
							bt_splash.setVisibility(View.GONE);
						}
						else if(index==2)
						{
							rl_splash.setBackgroundResource(R.drawable.splash_2);
							bt_splash.setVisibility(View.GONE);
						}
						else if(index==3)
						{
							rl_splash.setBackgroundResource(R.drawable.splash_3);
							bt_splash.setVisibility(View.VISIBLE);
						}else if(index>3)
						{
							rl_splash.setBackgroundResource(R.drawable.splash_3);
							bt_splash.setVisibility(View.VISIBLE);
						}
					}
					else if(moveX>30)
					{
						index--;
						if(index==1)
						{
							rl_splash.setBackgroundResource(R.drawable.splash_1);
							bt_splash.setVisibility(View.GONE);
						}
						else if(index==2)
						{
							rl_splash.setBackgroundResource(R.drawable.splash_2);
							bt_splash.setVisibility(View.GONE);
						}
						else if(index==3)
						{
							rl_splash.setBackgroundResource(R.drawable.splash_3);
							bt_splash.setVisibility(View.VISIBLE);
							
						}else if(index>3)
						{
							rl_splash.setBackgroundResource(R.drawable.splash_3);
							bt_splash.setVisibility(View.VISIBLE);
						}
					}
					
					
					
					
					break;
			}
				return true;
			}
			});
	}
	public void startmain(View v)
	{
		finish();
		startActivity(new Intent(SlideActivity.this,MyImage1Activity.class));
		
	}
}
