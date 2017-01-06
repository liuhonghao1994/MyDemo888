package com.atguigu.l10_app_animation;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ScannerActivity extends Activity {
	private ImageView iv_scanner;
	private TextView tv_scanner;
	private ProgressBar pb_scanner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scanner);
		iv_scanner = (ImageView) findViewById(R.id.iv_scanner);
		tv_scanner = (TextView) findViewById(R.id.tv_scanner);
		pb_scanner = (ProgressBar) findViewById(R.id.progressBar1);
		RotateAnimation animation = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animation.setDuration(1000);
		animation.setRepeatCount(Animation.INFINITE);
		animation.setInterpolator(new LinearInterpolator());
		iv_scanner.startAnimation(animation);
		// 使用异步任务 模拟进度条
		scanner();
	}

	private void scanner() {
		new AsyncTask<Void, Void, Void>() {
			// 主线程显示提示视图
			protected void onPreExecute() {
				//修改文本信息  正在扫描杀毒
				tv_scanner.setText("正在扫描杀毒");
				pb_scanner.setMax(100);
			};

			// 子线程执行 耗时操作
			@Override
			protected Void doInBackground(Void... params) {
				for (int i = 1; i <= 100; i++) {
					SystemClock.sleep(50);
					pb_scanner.incrementProgressBy(1);
				}
				return null;
			}

			// 主线程更新UI
			protected void onPostExecute(Void result) {
				// 更新文本显示 扫描停止
				tv_scanner.setText("扫描完成，您的手机一切正常，请放心使用");
				iv_scanner.clearAnimation();
				pb_scanner.setVisibility(View.GONE);
				
			};
		}.execute();
		//自定义水平方向progressbar
	}
	
}
