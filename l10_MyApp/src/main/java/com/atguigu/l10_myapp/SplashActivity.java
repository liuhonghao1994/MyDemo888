package com.atguigu.l10_myapp;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {
	private RelativeLayout rl;
	private ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		rl=(RelativeLayout) findViewById(R.id.rl);
		iv=(ImageView) findViewById(R.id.iv);
		AnimationSet set=new AnimationSet(true);
		set.setInterpolator(new LinearInterpolator());
		AlphaAnimation alpha=new AlphaAnimation(0, 1);
		alpha.setDuration(1500);
		set.addAnimation(alpha);
		set.setStartOffset(1000);
		rl.startAnimation(set);
		RotateAnimation rotate1=new RotateAnimation(0, 360,  Animation.RELATIVE_TO_SELF, 0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
		rotate1.setDuration(1500);
		rotate1.setRepeatCount(Animation.INFINITE);
		rotate1.setInterpolator(new LinearInterpolator());
		iv.startAnimation(rotate1);
		set.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				SystemClock.sleep(5000);
				finish();
				iv.clearAnimation();
				startActivity(new Intent(SplashActivity.this,SlideActivity.class));
			}
		});
	}
}
