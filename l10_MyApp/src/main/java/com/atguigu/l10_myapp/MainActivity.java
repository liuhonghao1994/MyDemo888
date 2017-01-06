package com.atguigu.l10_myapp;

import java.util.ArrayList;
import java.util.List;

import com.atguigu.l10_myapp_bean.MyData;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends Activity {
	private TextView tv1;
	private ListView lv_1;
	private List<MyData> data;
	private int[] image= new int[]{R.drawable.ff,R.drawable.ff10,R.drawable.zxcv};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv1=(TextView) findViewById(R.id.tv1);
		TranslateAnimation ta=new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 1f, Animation.RELATIVE_TO_PARENT, -1f, Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0f);
		ta.setDuration(4000);
		ta.setRepeatCount(Animation.INFINITE);
		tv1.startAnimation(ta);
		lv_1=(ListView) findViewById(R.id.lv_1);
		indinite();
		MyAdapter adpter=new MyAdapter();
		lv_1.setAdapter(adpter);
	}
	private void indinite() {
		data=new ArrayList<MyData>();
		for(int i=1;i<200;i++)
		{
			
			MyData da=new MyData("商票安选第"+2000+i+"期", (int)((Math.random()*99+1))+"%", (int)(Math.random()*83+7)+"", image[(int)(Math.random()*3)]);
			data.add(da);
		}
		
	}
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if(convertView==null)
			{
				holder=new ViewHolder();
				convertView=View.inflate(MainActivity.this, R.layout.item, null);
				holder.tv_qixian=(TextView)convertView.findViewById(R.id.tv_qixian);
				holder.tv_baifenshu=(TextView)convertView.findViewById(R.id.tv_baifenshu);
				holder.tv_day=(TextView)convertView.findViewById(R.id.tv_day);
				holder.iv_tupian=(ImageView)convertView.findViewById(R.id.iv_tupian);
				convertView.setTag(holder);
			}
			else{
				holder = (ViewHolder)convertView.getTag();
			}
			holder.tv_qixian.setText(data.get(position).getQixian());
			holder.tv_baifenshu.setText(data.get(position).getBaifenshu());
			holder.tv_day.setText(data.get(position).getDay());
			holder.iv_tupian.setImageResource(data.get(position).getImage());
			return convertView;
		}
		
	}
	class ViewHolder{
		TextView tv_qixian;
		TextView tv_baifenshu;
		TextView tv_day;
		ImageView  iv_tupian;
	}
	private long startime=0;
	@Override
	public void onBackPressed() {
		if(SystemClock.currentThreadTimeMillis()-startime>2000)
		{
			Toast.makeText(MainActivity.this, "您要点击两次才能退出去哦", LENGTH_SHORT).show();
			startime=System.currentTimeMillis();
			return;
		}
		this.finish();
	}
}
